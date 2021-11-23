package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestForm {

    private Long id;
    private StatusRequest status;
    @NotNull
    private String obs;
    private User user;
    private List<Plate> plates;
    private List<Long> platesId;

    public Request convertToUserRequest (RequestForm requestForm){
        return new Request(requestForm);
    }

}
