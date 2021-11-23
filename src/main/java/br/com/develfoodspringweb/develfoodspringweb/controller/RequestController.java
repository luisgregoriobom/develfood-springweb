package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.requestCommon.StatusPresent;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import br.com.develfoodspringweb.develfoodspringweb.service.StatusRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Luis Gregorio.
 *
 * Controller created to define endpoints and request methods
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final StatusRequestService statusRequestService;
    private final RequestService requestService;

    /**
     * Method to register new request
     * @param requestForm
     * @param uriBuilder
     * @return
     * @author: Thomas Benetti
     */
    @PostMapping
    public ResponseEntity<RequestDto> registerRequest(@RequestBody RequestForm requestForm,
                                                      UriComponentsBuilder uriBuilder){
        RequestDto requestToRegister = requestService.registerRequest(requestForm);

        if (requestToRegister == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to create the request");
        }

        URI uri = uriBuilder
                .path("/api/request/{id}")
                .buildAndExpand(requestToRegister.getId())
                .toUri();

        return ResponseEntity.created(uri).body(requestToRegister);
    }

    /**
     * Method to perform PUT, endpoint to change the status of an request.
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    @PutMapping("/statusRequest/{id}")
    @Transactional
    public ResponseEntity<StatusPresent> update(@PathVariable Long id, @RequestBody @Valid RequestFormUpdate form) {
       RequestDto statusUpdate = statusRequestService.update(id, form);
       StatusPresent present = statusRequestService.convertToPresent(statusUpdate);

        return ResponseEntity.ok().body(present);
    }

}






