package br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Luis Gregorio.
 *
 * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
 */
@Data
public class PlatePresent {

    private String name;
    private String description;
    private Double price;
    private Category category;
    private List<PlateDto> plateDtos;
    private String photo;
}
