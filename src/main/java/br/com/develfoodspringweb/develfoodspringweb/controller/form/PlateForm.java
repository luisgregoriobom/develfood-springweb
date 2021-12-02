package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.lang.Long;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlateForm {


    private String obs;
    @NotEmpty @NotNull @Length(min = 5)
    private String name;
    @NotEmpty @NotNull
    private String description;
    private int quantity = 1;
    @DecimalMin(value = "5.0", inclusive = false)
    private Double price;
    private Category category;
    private Long restaurantId;

    /**
     * Function to convert the object Form Class received into a Model Object.
     * @param plateForm
     * @return
     * @author: Thomas B.P.
     */
    public Plate convertToPlate(PlateForm plateForm){
        return new Plate(plateForm);
    }

}
