package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

@Data
public class RestaurantDto {

    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String email;
    private String address;
    private String phone;
    private List<Plate> plate;

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.cnpj = restaurant.getCnpj();
        this.login = restaurant.getLogin();
        this.email = restaurant.getEmail();
        this.address = restaurant.getAddress();
        this.phone = restaurant.getPhone();
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param restaurant
     * @return
     * @author: Thomas B.P.
     */
    public static RestaurantDto convertToRestaurantDto(Restaurant restaurant){
        return new RestaurantDto(restaurant);
    }

    /**
     * Function to convert the RestaurantDTO Object for a Restaurant List
     * @param restaurants
     * @return
     * @author: Luis Gregorio
     */
    public static List<RestaurantDto> converter(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantDto::new).collect(Collectors.toList());
    }
}
