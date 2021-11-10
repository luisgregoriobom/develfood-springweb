package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDto {

    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String email;
    private String address;
    private String phone;
    private String foodType;
    private List<Plate> plate;

    public RestaurantDto(String name, String foodType){
        this.name = name;
        this.foodType = foodType;
    }

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.cnpj = restaurant.getCnpj();
        this.login = restaurant.getLogin();
        this.email = restaurant.getEmail();
        this.address = restaurant.getAddress();
        this.phone = restaurant.getPhone();
        this.foodType = restaurant.getFoodType();
        this.plate = restaurant.getPlate();
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

}
