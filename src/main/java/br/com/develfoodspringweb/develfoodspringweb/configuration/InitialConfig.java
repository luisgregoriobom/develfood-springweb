package br.com.develfoodspringweb.develfoodspringweb.configuration;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitialConfig {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private PlateRepository plateRepository;


    /**
     * Function to insert manually data into database and tryout if it is working
     * @author: Thomas B.P.
     */
    public void configurar(){

        Restaurant restaurant = new Restaurant("testName03", "testCnpj03", "testPhone03", "testFootType03");
        restaurantRepository.save(restaurant);
    }

}
