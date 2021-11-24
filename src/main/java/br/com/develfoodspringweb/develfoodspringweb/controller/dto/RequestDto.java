package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class RequestDto {

    private Long id;
    private StatusRequest status;
    private User user;
    private String dateRequest;
    private List<PlateDto> plateDtos;
    private String obs;
    private List<Long> platesId;
    private Double priceTotal;
    @JsonIgnore
    private List<Plate> plates;

    public RequestDto(Request request){
        this.id = request.getId();
        this.status = request.getStatus();
        this.dateRequest = request.getDateRequest();
        this.user = request.getUser();
        this.obs = request.getObs();
        this.priceTotal = request.getPriceTotal();
        this.plates = request.getPlateId();
        this.converToListDto(request.getPlateId());
    }


    /**
     * Function to convert PlateModel class, receveid when creating a request, to a list of DTO class which is called from the constructor above in this class
     * @param plates
     * @return
     * @author: Thomas Benetti
     */
    private void converToListDto(List<Plate> plates) {
        if(this.plateDtos == null){
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