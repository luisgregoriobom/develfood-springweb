package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.RequestPresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.RequestPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

