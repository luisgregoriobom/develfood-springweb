package br.com.develfoodspringweb.develfoodspringweb.controller.userCommon;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Luis Gregorio.
 *
 * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
 */
@Data
public class PlatePresentUser {

    private String name;
    private String description;
    private Double price;
    private Category category;
    private RestaurantPresentUser restaurant;
}
