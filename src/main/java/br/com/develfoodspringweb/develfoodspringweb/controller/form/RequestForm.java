package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestForm {

    private Long id;
    private StatusRequest status;
    private LocalDateTime dateRequest;
    private String obs;
    private User user;
    private List<Plate> plates;

    public UserRequest convertToUserRequest (RequestForm requestForm){
        return new UserRequest(requestForm);
    }

}
