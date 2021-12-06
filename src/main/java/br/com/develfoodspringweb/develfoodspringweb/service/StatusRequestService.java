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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StatusRequestService {

    private final RequestRepository requestRepository;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final RestaurantRepository restaurantRepository;

    /**
     * Function to update the status of a request then the user receives an email of update
     * @param id
     * @param form
     * @param emailDto
     * @return
     * @author: Luis Gregorio, Thomas Benetti
     */
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
            Map<String, Object> variables = new HashMap<>();
            variables.put("user", user.getName());
            variables.put("request", request.getId());
            variables.put("requestStatus", request.getStatus());
            variables.put("requestPrice", request.getPriceTotal());
            variables.put("requestDate", request.getDateRequest());
            variables.put("restaurantName", currentRestaurant.get().getName());
            context.setVariables(variables);
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
