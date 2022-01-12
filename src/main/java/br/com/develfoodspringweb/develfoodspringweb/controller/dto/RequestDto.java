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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private Double priceTotal;
    @JsonIgnore
    private List<Plate> plates;


    public RequestDto(Long id, StatusRequest status, String dateRequest, User user,
                      String obs, Double priceTotal, List<Plate> plates){
        this.id = id;
        this.status = status;
        this.dateRequest = dateRequest;
        this.user = user;
        this.obs = obs;
        this.priceTotal =priceTotal;
        this.plateDtos = plates.stream().map(PlateDto::new).collect(Collectors.toList());
    }

    public RequestDto(Request request){
        this(request.getId(), request.getStatus(), request.getDateRequest(),
                null, request.getObs(), request.getPriceTotal(), request.getPlateId());
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

    public static List<RequestDto> convertToListDto(List<Request> requests){
        return requests.stream().map(RequestDto::new).collect(Collectors.toList());
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