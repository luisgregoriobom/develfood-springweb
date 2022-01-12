package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PlateDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private int quantity;
    private Double priceTotal;
    private String restaurantName;
    @JsonIgnore
    private List<Plate> plates;
    private String photo;
    private Long restaurantId;
    private String obs;


    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.description = plate.getDescription();
        this.price = plate.getPrice();
        this.category = plate.getCategory();
        this.restaurantName = plate.getRestaurant().getName();
        this.plates = plate.getPlateName();
        this.photo = plate.getPhoto();
        this.restaurantId = plate.getRestaurant().getId();
        this.obs = plate.getObs();

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
     * Function to convert Model class to a list of DTO class
     * @param plates
     * @return
     * @author: Thomas Benetti
     */
    public static List<PlateDto> converToListDto(List<Plate> plates) {
        return plates.stream().map(PlateDto::new).collect(Collectors.toList());
    }
}
