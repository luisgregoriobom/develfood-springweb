package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by Luis Gregorio.
 *
 * DTO class to pass model class attributes without entity relationship
 */
@Data
public class RequestDto {


    private Long id;
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private User user;
    private LocalDateTime dateRequest;
    private List<Plate> plate;
    private String obs;

    public RequestDto(Request request) {
        this.id = request.getId();
        this.status = request.getStatus();
        this.user = request.getUser();
        this.dateRequest = request.getDateRequest();
        this.plate = request.getPlate();
        this.obs = getObs();
    }
}