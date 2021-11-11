package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.lang.Long;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PlateForm {

    @NotEmpty @NotNull @Length(min = 5)
    private String name;
    @NotEmpty @NotNull @Length(min = 10)
    private String obs;
    @DecimalMin(value = "5.0", inclusive = false)
    private BigDecimal price;
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
