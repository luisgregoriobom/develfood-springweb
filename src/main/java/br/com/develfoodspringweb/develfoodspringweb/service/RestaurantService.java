package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.FilterForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * Function that make a query with the name of the restaurant as parameter and check in the database if the name is present
     * @param nameRestaurant
     * @return
     * @author: Thomas B.P.
     */
    public RestaurantDto getRestaurantByName(String nameRestaurant){
        Optional<Restaurant> opt = restaurantRepository.findByName(nameRestaurant);
        if (!opt.isPresent()){
            return null;
        }
        return RestaurantDto.convertToRestaurantDto(opt.get());
    }

    /**
     * Function to register new Restaurant
     * @param restaurantForm
     * @return
     * @author: Thomas B.P.
     */
    public RestaurantDto register (RestaurantForm restaurantForm){

        Restaurant restaurant = restaurantForm.convertToRestaurant(restaurantForm);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            String encodedPassword = passwordEncoder.encode(restaurantForm.getPassword());
            restaurant.setPassword(encodedPassword);
        } catch (Exception e){
            return null;
        }

        restaurantRepository.save(restaurant);
        if (restaurant.getId() == null){
            return null;
        }
        return new RestaurantDto(restaurant);
    }

    /**
     * Implements the filter by name or food type of the restaurant and return a list of the searches
     * @param filterForm
     * @param pageable
     * @return
     * @author: Thomas B.P.
     */
    public List<RestaurantDto> filter (FilterForm filterForm, Pageable pageable){

        Pageable pageByFilter = PageRequest.of(filterForm.getSkip()
                , filterForm.getTake()
                , Sort.by(Sort.Direction.ASC, "id"));

        Page<Restaurant> restaurants = restaurantRepository.findAll(Specification
                .where(RestaurantRepository.filterByName(filterForm.getSearch().toLowerCase()))
                .or(RestaurantRepository.filterByFoodType(filterForm.getSearch().toLowerCase()))
                ,pageByFilter);

        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        restaurants.stream().map(restaurant -> restaurantDtoList.add(new RestaurantDto(restaurant.getName(), restaurant.getFoodType()))).collect(Collectors.toList());
        return restaurantDtoList;
    }

}
