package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Luis Gregorio.
 *
 * In this class we can define what data a plate can update in the system.
 */
@Data
public class PlateFormUpdate {

    @NotEmpty @NotNull @Length(min = 5)
    private String name;
    @NotEmpty @NotNull @Length(min = 10)
    private String description;
    @DecimalMin(value = "5.0", inclusive = false)
    private Double price;
    private String photo;

    public Plate update(Long id, PlateRepository plateRepository) {
        Plate plate = plateRepository.getById(id);
        plate.setName(this.name);
        plate.setDescription(this.description);
        plate.setPrice(this.price);
        plate.setPhoto(this.photo);
        return plate;
    }

    /**
     * Function to convert the object Form Class received into a Model Object
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    public Plate convertToPlateUpdate(PlateFormUpdate form) {
        return new Plate();
    }
}
