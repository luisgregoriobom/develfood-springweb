package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDto {

    private Long id;
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private String dateRequest;
    private User user;
    private List<PlateDto> plateDtos;
    private List<Long> platesId;
    private String obs;
    private List<Plate> plate;
    private Double priceTotal;

    public RequestDto(Request request){
        this.id = request.getId();
        this.status = request.getStatus();
        this.dateRequest = request.getDateRequest();
        this.user = request.getUser();
        this.obs = request.getObs();
        this.priceTotal = request.getPriceTotal();
        this.plate = request.getPlate();
        this.converToListDto(request.getPlates());
        }


    /**
     * Function to convert PlateModel class, receveid when creating a request, to a list of DTO class which is called from the constructor above in this class
     * @param plates
     * @return
     * @author: Thomas Benetti
     */
    private void converToListDto(List<Plate> plates){
        if (this.plateDtos == null){
            this.plateDtos = new ArrayList<>();
        }
        plates.stream().forEach(plate -> this.plateDtos.add(new PlateDto(plate)));
    }

    /**
     * Function to convert Model class to a list of DTO class
     * @param request
     * @return
     * @author: Thomas Benetti
     */
    public static RequestDto convertToRequestDto (Request request){
        return new RequestDto(request);
    }
}

/**
 * Created by Luis Gregorio.
 *
 * DTO class to pass model class attributes without entity relationship
 */
@Data
public class RequestDto {


    private Long id;
    private User user;
    private LocalDateTime dateRequest;
    private List<Plate> plate;
    private String obs;
}