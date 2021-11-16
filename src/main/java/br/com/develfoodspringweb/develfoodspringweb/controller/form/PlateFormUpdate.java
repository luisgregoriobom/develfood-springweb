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
    private String obs;
    @DecimalMin(value = "5.0", inclusive = false)
    private BigDecimal price;

    public Plate update(Long id, PlateRepository plateRepository) {
        Plate plate = plateRepository.getById(id);
        plate.setName(this.name);
        plate.setDescription(this.obs);
        plate.setPrice(this.price);

        return plate;
    }
}
