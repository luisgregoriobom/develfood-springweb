package br.com.develfoodspringweb.develfoodspringweb.service;


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
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StatusRequestService {

    private final RequestRepository requestRepository;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
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
            Context context = new Context();
            context.setVariable("user", user.getName());
            context.setVariable("request", request.getId());
            context.setVariable("requestStatus", request.getStatus());
            context.setVariable("requestPrice", request.getPriceTotal());
            context.setVariable("requestDate", request.getDateRequest());
            context.setVariable("restaurantName", currentRestaurant.get().getName());
            String htmlBody = templateEngine.process("emailStatusRequest.html", context);

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setText(htmlBody, true);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(emailDto.getEmailSubject() + request.getId());
            emailSender.send(mimeMessage);

            emailDto.setEmailStatus(EmailStatus.SENT);
        } catch (MailException | MessagingException e){
            emailDto.setEmailStatus(EmailStatus.ERROR);
        }
        return new RequestDto(request);
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
