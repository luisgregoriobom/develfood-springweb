package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlateDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private String restaurantName;
    @JsonIgnore
    private List<Plate> plates;
    private String photo;


    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.description = plate.getDescription();
        this.price = plate.getPrice();
        this.category = plate.getCategory();
        this.restaurantName = plate.getRestaurant().getName();
        this.plates = plate.getPlateName();
        this.photo = plate.getPhoto();
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
