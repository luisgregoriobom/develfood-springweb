package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created By Luis Gregorio
 *
 * Class created to collect information from the application and send it to the API
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestForm {


    private Long restaurantId;
    @NotNull
    private String obs;
    private List<Plate> plates;
    private List<Plate> platesId;

    public Request convertToUserRequest (RequestForm requestForm){
        return new Request(requestForm);
    }
}
