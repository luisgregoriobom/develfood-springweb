package br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon;

import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Luis Gregorio.
 *
 * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
 */
@Data
public class RequestPresent {

    private Long id;
    private LocalDateTime dateRequest;
    private StatusRequest status;
    private String obs;
    private List<PlatePresent> plate;
    private UserPresent user;
}
