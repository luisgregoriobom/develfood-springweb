package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<RequestDto> registerRequest(@RequestBody RequestForm requestForm,
                                                      UriComponentsBuilder uriBuilder){
        RequestDto requestToRegister = requestService.registerRequest(requestForm);

        URI uri = uriBuilder
                .path("/api/request/{id}")
                .buildAndExpand(requestToRegister.getId())
                .toUri();

        return ResponseEntity.created(uri).body(requestToRegister);

    }

}
