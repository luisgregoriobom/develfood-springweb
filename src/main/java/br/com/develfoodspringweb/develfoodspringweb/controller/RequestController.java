package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import br.com.develfoodspringweb.develfoodspringweb.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Luis Gregorio.
 *
 * Controller created to define endpoints and request methods
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestRepository requestRepository;

    @PutMapping("/statusRequest/{id}")
    public UserRequest changeStatus(@PathVariable Long id) {
        UserRequest userRequest = requestRepository.findByIdRequest(id);
        if(userRequest.getStatus()) {
            userRequest.setStatus(userRequest.getStatus());
            requestRepository.save(userRequest);
        } else if (userRequest.getRequestStatus().equals(RequestStatusType.DELIVERED)) {
            requestRepository.delete(userRequest);
        }
        return userRequest;
    }




//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<RestaurantDto> update(@PathVariable Long id, @RequestBody @Valid RestaurantFormUpdate form){
//        RestaurantDto restaurantUpdate = restaurantService.update(id, form);
//        if(restaurantUpdate == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Restaurant Not Found");
//        }
//        return ResponseEntity.ok(restaurantUpdate);
//    }
    

}

