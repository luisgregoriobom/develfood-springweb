package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RequestForm;
import br.com.develfoodspringweb.develfoodspringweb.service.RequestService;
import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.RequestPresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.RequestPresentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
;


/**
 * Created by Luis Gregorio.
 *
 * Controller created to define endpoints and request methods
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/request")
public class RequestController {

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
     * Method created to list all requests that a restaurant has.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/RestaurantRequest/{id}")
    public ResponseEntity<RequestPresent> filterRestaurantId(@PathVariable("id") Long id) {
        RequestDto dto = requestService.searchRequestId(id);
        RequestPresent present = requestService.convertToPresent(dto);

        return ResponseEntity.ok().body(present);
    }

    /**
     * Method created to list a request that a user has.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/UserRequest/{id}")
    public ResponseEntity<RequestPresentUser> filterUserId(@PathVariable("id") Long id) {
        RequestDto dto = requestService.searchRequestId(id);
        RequestPresentUser present = requestService.convertToUserPresent(dto);

        return ResponseEntity.ok().body(present);
    }
}

