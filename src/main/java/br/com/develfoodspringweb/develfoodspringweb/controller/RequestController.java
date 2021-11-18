package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.PlatePresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.RequestPresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon.UserPresent;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RequestDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.PlatePresentUser;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.RequestPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.RestaurantPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.controller.userCommon.UserPresentUser;
import br.com.develfoodspringweb.develfoodspringweb.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        RequestPresent present = convertToPresent(dto);

        return ResponseEntity.ok().body(present);
    }

    /**
     * Method created to use the attributes coming from the Present classes,
     * setting and simplifying the information in the request body
     *
     * @param dto
     * @return
     * @author: Luis Gregorio
     */
    private RequestPresent convertToPresent(RequestDto dto) {
        RequestPresent present = new RequestPresent();
        present.setId(dto.getId());
        present.setStatus(dto.getStatus());
        present.setObs(dto.getObs());
        present.setDateRequest(dto.getDateRequest());
        List<PlatePresent> platePresents = new ArrayList<>();
        dto.getPlate().forEach(plate ->{
            PlatePresent platePresent = new PlatePresent();
            platePresent.setObs(plate.getObs());
            platePresent.setPrice(plate.getPrice());
            platePresent.setCategory(plate.getCategory());
            platePresent.setName(plate.getName());
            platePresents.add(platePresent);
        });

        UserPresent userPresent = new UserPresent();
        userPresent.setName(dto.getUser().getName());
        userPresent.setAddress(dto.getUser().getAddress());
        userPresent.setCpf(dto.getUser().getCpf());
        userPresent.setEmail(dto.getUser().getEmail());
        userPresent.setPhone(dto.getUser().getPhone());
        present.setUser(userPresent);
        present.setPlate(platePresents);

        return present;
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
        RequestPresentUser present = convertToUserPresent(dto);

        return ResponseEntity.ok().body(present);
    }

    /**
     * Method created to use the attributes coming from the Present classes,
     * setting and simplifying the information in the request body
     *
     * @param dto
     * @return
     * @author: Luis Gregorio
     */
    private RequestPresentUser convertToUserPresent(RequestDto dto) {
        RequestPresentUser present = new RequestPresentUser();
        present.setId(dto.getId());
        present.setStatus(dto.getStatus());
        present.setObs(dto.getObs());
        present.setDateRequest(dto.getDateRequest());
        List<PlatePresentUser> platePresents = new ArrayList<>();
        dto.getPlate().forEach(plate ->{
            PlatePresentUser platePresent = new PlatePresentUser();
            platePresent.setObs(plate.getObs());
            platePresent.setPrice(plate.getPrice());
            platePresent.setCategory(plate.getCategory());
            platePresent.setName(plate.getName());
            RestaurantPresentUser restaurant = new RestaurantPresentUser();
            restaurant.setName(plate.getRestaurant().getName());
            platePresent.setRestaurant(restaurant);
            platePresents.add(platePresent);
        });

        UserPresentUser userPresent = new UserPresentUser();
        userPresent.setAddress(dto.getUser().getAddress());
        userPresent.setPhone(dto.getUser().getPhone());
        present.setUser(userPresent);
        present.setPlate(platePresents);

        return present;
    }
}

