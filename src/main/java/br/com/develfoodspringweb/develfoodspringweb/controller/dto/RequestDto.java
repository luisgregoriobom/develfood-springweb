package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
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

    public RequestDto(Request request){
        this.id = request.getId();
        this.status = request.getStatus();
        this.dateRequest = request.getDateRequest();
        this.user = request.getUser();
        this.plate = request.getPlate();
    }

    public static RequestDto convertToRequestDto (Request request){
        return new RequestDto(request);
    }
}
