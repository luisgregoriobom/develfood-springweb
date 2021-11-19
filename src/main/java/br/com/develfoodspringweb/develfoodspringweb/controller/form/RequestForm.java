package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created By Luis Gregorio
 *
 * Class created to collect information from the application and send it to the API
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestForm {

    private Long id;
    private StatusRequest status;
    private LocalDateTime dateRequest;
    private String obs;
    private List<Plate> plates;

}