package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestDto {

    private Long id;
    private Enum status;
    private LocalDateTime dateRequest;
    private User user;
    private List<Plate> plate;

    public RequestDto(UserRequest userRequest){
        this.id = userRequest.getId();
        this.status = userRequest.getStatus();
        this.dateRequest = userRequest.getDateRequest();
        this.user = userRequest.getUser();
        this.plate = userRequest.getPlate();
    }

    public static RequestDto convertToRequestDto (UserRequest userRequest){
        return new RequestDto(userRequest);
    }
}
