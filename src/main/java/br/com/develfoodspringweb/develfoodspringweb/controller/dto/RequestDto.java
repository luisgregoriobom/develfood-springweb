package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDto {

    private Long id;
    private Enum status;
    private LocalDateTime dateRequest;
    private User user;
    private List<Plate> plate;
    private String obs;
    private Double priceTotal;

    public RequestDto(Request request){
        this.id = request.getId();
        this.status = request.getStatus();
        this.dateRequest = request.getDateRequest();
        this.user = request.getUser();
        this.plate = request.getPlate();
        this.obs = request.getObs();
        this.priceTotal = request.getPriceTotal();
    }

    public static RequestDto convertToRequestDto (Request request){
        return new RequestDto(request);
    }
}
