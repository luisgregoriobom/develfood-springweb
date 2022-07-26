package br.com.develfoodspringweb.develfoodspringweb.controller;


import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.FilterForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantPasswordUpdateForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;

    /**
     * Method GET to list all restaurants
     * @param restaurantName
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/ListAll")
    @Transactional
    public List<RestaurantDto> list(String restaurantName) {
        if (restaurantName == null) {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            return RestaurantDto.convertToListDto(restaurants);
        } else {
            List<Restaurant> restaurants = restaurantRepository.findByRestaurantName(restaurantName);
            return RestaurantDto.convertToListDto(restaurants);
        }
    }

    /**
     * Function with GET method to do make a query with the name of the restaurant as parameter.
     * @param nameRestaurant
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    @Transactional
    public ResponseEntity<RestaurantDto> getRestaurantByName(@RequestParam String nameRestaurant){
        if(nameRestaurant == null){
            return null;
        }
        RestaurantDto queryByName = restaurantService.getRestaurantByName(nameRestaurant);
        if (queryByName == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Restaurant name not found");
        }
        return  new ResponseEntity<>(queryByName, HttpStatus.OK);
    }

    /**
     * Function with POST method to register new Restaurant while the function create the URI route and return the head HTTP location with the URL
     * @param restaurantForm
     * @param uriComponentsBuilder
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping
    public ResponseEntity<RestaurantDto> register(@RequestBody @Valid RestaurantForm restaurantForm,
                                                  UriComponentsBuilder uriComponentsBuilder){

        RestaurantDto restaurantToRegister = restaurantService.register(restaurantForm);
        if (restaurantToRegister == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Restaurant not created.");
        }
        if (restaurantForm.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error encrypting password.");
        }

        URI uri = uriComponentsBuilder
                .path("{id}")
                .buildAndExpand(restaurantToRegister.getId())
                .toUri();

        return ResponseEntity.created(uri).body(restaurantToRegister);
    }

    /**
     * Function to filter by the name and or food type of the restaurant and return a list of the search pageable
     * @param filterForm
     * @param pageable
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping("/filter")
    public ResponseEntity<List<RestaurantDto>> filter(@RequestBody (required = false) FilterForm filterForm,
                                                      Pageable pageable){

        List<RestaurantDto> listOfFilter = restaurantService.filter(filterForm, pageable);
        return new ResponseEntity<>(listOfFilter, HttpStatus.OK);
    }

    /**
     * Method to detail an already registered restaurant.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RestaurantDto> details(@PathVariable Long id) {
        RestaurantDto restaurantDetail = restaurantService.details(id);
        if(restaurantDetail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                            "Restaurant Not Found");
        }
        return ResponseEntity.ok(restaurantDetail);
    }

    /**
     * Method for updating the data of an already registered restaurant.
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<RestaurantDto> update(@RequestBody @Valid RestaurantFormUpdate form){
        RestaurantDto restaurantUpdate = restaurantService.update(form);
        if(restaurantUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                            "Restaurant Not Found");
        }
        return ResponseEntity.ok(restaurantUpdate);
    }

    /**
     * Method to update the restaurant's password only.
     * @param passwordUpdateForm
     * @return
     * @author: Thomas B.P.
     */
    @PutMapping("/update-password")
    @Transactional
    public ResponseEntity<RestaurantDto> updatePassword (@RequestBody @Valid RestaurantPasswordUpdateForm passwordUpdateForm){
        RestaurantDto restaurantUpdate = restaurantService.updatePassword(passwordUpdateForm);
        if (restaurantUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        return ResponseEntity.ok(restaurantUpdate);
    }

    /**
     * Method to delete a restaurant from the database.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){
        RestaurantDto restaurantRemove = restaurantService.remove(id);
        if(restaurantRemove == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Restaurant Not Found");
        }
        return ResponseEntity.ok().build();
    }

}