package br.com.develfoodspringweb.develfoodspringweb.controller.dto;


import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Data;
import java.lang.Long;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Function to convert the PlateDTO Object for a Plate List
     * @param plates
     * @return
     * @author: Luis Gregorio
     */
    public static List<PlateDto> converter(List<Plate> plates) {
        return plates.stream().map(PlateDto::new).collect(Collectors.toList());
    }
}
