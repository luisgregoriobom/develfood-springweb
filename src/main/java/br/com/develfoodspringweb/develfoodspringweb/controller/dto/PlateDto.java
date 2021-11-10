package br.com.develfoodspringweb.develfoodspringweb.controller.dto;


import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import lombok.Data;
import java.lang.Long;
import java.math.BigDecimal;

@Data
public class PlateDto {

    private Long id;
    private String name;
    private String obs;
    private BigDecimal price;
    private Enum category;
    private String restaurantName;
    private Long restaurantId;

    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.obs = plate.getObs();
        this.price = plate.getPrice();
        this.category = plate.getCategory();
        this.restaurantName = plate.getRestaurant().getName();
        this.restaurantId = plate.getRestaurant().getId();
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param plates
     * @return
     * @author: Thomas B.P.
     */

    public static PlateDto convertToPlateDto(Plate plates){
        return new PlateDto(plates);

    }
}
