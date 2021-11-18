package br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Luis Gregorio.
 *
 * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
 */
@Data
public class PlatePresent {

    private String name;
    private String obs;
    private BigDecimal price;
    private Category category;
}
