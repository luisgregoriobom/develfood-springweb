package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestDto registerRequest(RequestForm requestForm){
        Request request = requestForm.convertToUserRequest(requestForm);
        request.getUser().getId();
        requestRepository.save(request);
        return new RequestDto(request);
    }

}
