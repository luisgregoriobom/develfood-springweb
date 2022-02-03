package br.com.develfoodspringweb.develfoodspringweb.jobs;


import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.models.EmailStatus;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class BirthdayJob {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender emailSender;


    /**
     * Job to send email to users with one month of registration. Triggered everyday at 18:00
     * @author: Thomas Benetti
     */
    @Scheduled(cron = "0 0 18 * * *")
    public void oneMonthBirthday() {

        List<User> userList = userRepository.findOneMonthBirthdayUsers(LocalDateTime.now());
        List<UserDto> userDtoList = new ArrayList<>();
        userList.stream().map(user ->
                userDtoList.add(new UserDto(user.getEmail()))).collect(Collectors.toList());
        List<String> userEmails = userDtoList.stream().map(UserDto::getEmail).collect(Collectors.toList());

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setText("Olá serumano, toma aqui seu presente de um mês de cadastro conosco!",
                    false);

            userEmails.forEach((email) -> {
                try{
                    mimeMessage.addRecipients(Message.RecipientType.TO, email);
                }catch (MessagingException e){
                    e.printStackTrace();
                }
            });

            emailSender.send(mimeMessage);

        } catch (MailException | MessagingException e) {

        }
    }

    /**
     * Job to send email to the monthly birthday users of registration. Triggered everyday at 12:00
     * @author: Thomas Benetti
     */
    @SneakyThrows
    @Scheduled(cron = "0 0 12 * * *")
    public void monthlyBirthday(){
        List<User> userList = userRepository.findMonthlyBirthdayUsers(LocalDateTime.now());
        List<UserDto> userDtoList = UserDto.convertToListDto(userList);
        List<String> userEmails = userDtoList.stream().map(UserDto::getEmail).collect(Collectors.toList());

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setText("Fala meu bruxo, toma tua promo mensal, tmj fml",
                false);

        userEmails.forEach((email) -> {
            try {
                mimeMessage.addRecipients(Message.RecipientType.TO, email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        emailSender.send(mimeMessage);

    }

}
