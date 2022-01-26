package br.com.develfoodspringweb.develfoodspringweb.jobs;


import br.com.develfoodspringweb.develfoodspringweb.models.EmailStatus;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@EnableScheduling
public class BirthdayJob {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender emailSender;


//    @Scheduled(cron = "0 * * * * *") //cron = "0 0 18 ? * * *" todo dia as 18hrs
//    public void teste1() {
//
//        Optional<User> userList = userRepository.findByRegistrationDate();
//
//
//        try {
//            MimeMessage mimeMessage = emailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            mimeMessageHelper.setText("teste dianho01", false);
//            mimeMessageHelper.setTo("develfoodii@gmail.com");
//            emailSender.send(mimeMessage);
//        } catch (MailException | MessagingException e) {
//
//        }
//    }

}
