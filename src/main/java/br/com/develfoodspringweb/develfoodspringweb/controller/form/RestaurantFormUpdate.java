package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Luis Gregorio.
 *
 * In this class we can define what data a restaurant can update in the system.
 */
@Data
public class RestaurantFormUpdate {

    @NotNull @NotEmpty @Length(min = 5)
    private String address;
    @NotNull @NotEmpty @Length(min = 11)
    private String phone;

    /**
     * Method to call Restaurant data update.
     * @param id
     * @param restaurantRepository
     * @return
     * @author: Luis Gregorio
     */
    public Restaurant update(Long id, RestaurantRepository restaurantRepository) {
        Restaurant restaurant = restaurantRepository.getById(id);
        restaurant.setAddress(this.address);
        restaurant.setPhone(this.phone);

        return restaurant;
    }
}
