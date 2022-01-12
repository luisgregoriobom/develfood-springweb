package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RestaurantPasswordUpdateForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String password;

    /**
     * Function to find the restaurant who desire to update his password and change it
     * @param id
     * @param restaurantRepository
     * @return
     * @author: Thomas B.P.
     */
    public Restaurant updatePassword (Long id, RestaurantRepository restaurantRepository){
        Restaurant restaurant = restaurantRepository.getById(id);
        restaurant.setPassword(this.password);

        return restaurant;
    }

}
