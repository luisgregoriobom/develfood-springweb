package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestForm {

    private Long id; //preciso retornar ele?
    private StatusRequest status; //posso setar o inicial pra WAITING TO ACCEPT naum?
    private LocalDateTime dateRequest; // = now?
    private String obs;
    private User user; //precisa?
    private List<Plate> plates;

    public Request convertToUserRequest (RequestForm requestForm){
        return new Request(requestForm);
    }

}
