package br.com.develfoodspringweb.develfoodspringweb.jobs;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.EmailDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeeklyJob implements Job {

    @Autowired
    private WeeklyJobService jobService;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TemplateEngine templateEngine;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobService.executeSampleJob();

        EmailDto emailDto = new EmailDto();

        System.out.println("SEND EMAIL WITH OFFERS FOR ALL USERS " + new Date());

        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        userList.stream().map(user ->
                userDtoList.add(new UserDto(user.getEmail()))).collect(Collectors.toList());
        List<String> userEmails = userDtoList.stream().map(UserDto::getEmail).collect(Collectors.toList());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        Context context = new Context();
        String htmlBody = templateEngine.process("weeklyJob.html", context);
        mimeMessageHelper.setText(htmlBody, true);
        mimeMessageHelper.setSubject(emailDto.getEmailWeeklyJob());
        userEmails.forEach((email) -> {
            try {
                mimeMessage.addRecipients(Message.RecipientType.TO, email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        javaMailSender.send(mimeMessage);
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(WeeklyJob.class)
                .storeDurably()
                .withIdentity("Qrtz_Job_Detail")
                .withDescription("Invoke Sample Job service...")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("Qrtz_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 ? 1-12 2"))
                .build();
    }
}
