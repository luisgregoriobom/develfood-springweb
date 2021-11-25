package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.EmailConfigDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.EmailDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.requestCommon.StatusPresent;
import br.com.develfoodspringweb.develfoodspringweb.models.EmailStatus;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StatusRequestService {

    private final RequestRepository requestRepository;
    private final JavaMailSender emailSender;
    private final RestaurantRepository restaurantRepository;

    public RequestDto update(Long id, RequestFormUpdate form, EmailDto emailDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentRestaurantAuth = authentication.getName();
        Optional<Restaurant> currentRestaurant = restaurantRepository.findByEmail(currentRestaurantAuth);
        if (!currentRestaurant.isPresent()){
            return null;
        }

        Optional<Request> opt = requestRepository.findById(id);
        User user = opt.get().getUser();
        if (!opt.isPresent()) {
            return null;
        }
        Request request = form.update(id, requestRepository);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(currentRestaurant.get().getEmail());
            message.setTo(user.getEmail());
            message.setSubject("TesteCabeçalho " + user.getName());
            message.setText("eeeeeeeeeeeita" + request.getStatus());
            emailSender.send(message);

            emailDto.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e){
            emailDto.setEmailStatus(EmailStatus.ERROR);
        }
        return new RequestDto(request);
    }

    public void sendEmail(EmailDto emailDto){

//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(this.emailConfigDto.getHost());
//        mailSender.setPort(this.emailConfigDto.getPort());
//        mailSender.setUsername(this.emailConfigDto.getUsername());
//        mailSender.setPassword(this.emailConfigDto.getPassword());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentRestaurantAuth = authentication.getName();
        Optional<Restaurant> currentRestaurant = restaurantRepository.findByEmail(currentRestaurantAuth);

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(currentRestaurant.get().getEmail());
            mailMessage.setTo("teste@qualquercoisa.com");
            mailMessage.setSubject("Teste Subject" + emailDto.getEmailFrom());
            mailMessage.setText(emailDto.getEmailText());
            emailSender.send(mailMessage);

            emailDto.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e){
            emailDto.setEmailStatus(EmailStatus.ERROR);
        }

    }

    /**
     * Method for setting the information that will appear in the JSON response
     * @param dto
     * @return
     * @author: Luis Gregorio
     */
    public StatusPresent convertToPresent(RequestDto dto) {
        StatusPresent present = new StatusPresent();
        present.setId(dto.getId());
        present.setStatus(dto.getStatus());
        present.setDateRequest(dto.getDateRequest());

        return present;
    }
}
