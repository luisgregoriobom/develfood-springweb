package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.EmailDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.EmailStatus;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final TemplateEngine templateEngine;
    private final JavaMailSender emailSender;

    /**
     * Function that make a query with the name of the user as parameter and check in the database if the name is present
     * @param nameUser
     * @return
     * @author: Thomas B.P.
     */
    public UserDto getUserByName(String nameUser){
        Optional<User> opt = userRepository.findByName(nameUser);
        if (!opt.isPresent()){
            return null;
        }
        return UserDto.convertToUserDto(opt.get());
    }

    /**
     * Function to register new User
     * @param
     * @return
     * @author: Thomas B.P, Luis Gregorio
     */
    public UserDto register(UserForm userForm, EmailDto emailDto){
        User user = userForm.convertToUser(userForm);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            String photo = user.getPhoto();
            String encodedPassword = passwordEncoder.encode(userForm.getPassword());
            user.setPassword(encodedPassword);
            user.setPhoto(photo);

            Context context = new Context();
            Map<String, Object> variables = new HashMap<>();
            variables.put("user", user.getName());
            context.setVariables(variables);
            String htmlBody = templateEngine.process("userRegister.html", context);

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setText(htmlBody, true);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(emailDto.getEmailSubjectUser());
            emailSender.send(mimeMessage);
            emailDto.setEmailStatus(EmailStatus.SENT);

        } catch (MailException | MessagingException e){
            emailDto.setEmailStatus(EmailStatus.ERROR);
        }
        userRepository.save(user);
        if (user.getId() == null) {
            return null;
        }
        return new UserDto(user);
    }

    /**
     * Function to detail a User information
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public UserDto details(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        return new UserDto(user.get());
    }

    /**
     * Function to Update a User data with encrypt code.
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    public UserDto update(Long id, UserFormUpdate form) {
        User userUpdate = form.convertToUserUpdate(form);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try{
            String encodedPassword = passwordEncoder.encode(form.getPassword());
            form.setPassword(encodedPassword);

        } catch(Exception e) {
            return null;
        }
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = form.update(id, userRepository);
            String photo = userUpdate.getPhoto();
            form.setPhoto(photo);
            return new UserDto(user);
        }
        return null;
    }

    /**
     * Function to remove a User
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public UserDto remove(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return new UserDto(user.get());
        }
        return null;
    }
}
