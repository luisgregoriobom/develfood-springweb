package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    public RequestDto registerRequest(RequestForm requestForm){
        Request request = requestForm.convertToUserRequest(requestForm);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAuth = authentication.getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserAuth);
        if (!currentUser.isPresent()){
            return null;
        }
        request.setUser(currentUser.get());
        requestRepository.save(request);
        return new RequestDto(request);
    }

}
