package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
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






