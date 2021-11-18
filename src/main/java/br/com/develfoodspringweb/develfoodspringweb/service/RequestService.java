package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    private final PlateRepository plateRepository;

    public RequestDto registerRequest(RequestForm requestForm){
        Request request = requestForm.convertToUserRequest(requestForm);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAuth = authentication.getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserAuth);
        if (!currentUser.isPresent()){
            return null;
        }

        List<Plate> platesFromRequest = plateRepository.findAllById(requestForm.getPlatesId());
        platesFromRequest.stream().forEach(pl -> {
            Double preco = request.getPriceTotal() + pl.getPrice();
            request.setPriceTotal(preco);
        });

        request.setUser(currentUser.get());
        requestRepository.save(request);
        request.setUser(null);
        return new RequestDto(request);
    }

}
