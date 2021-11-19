package br.com.develfoodspringweb.develfoodspringweb.controller.requestCommon;

import br.com.develfoodspringweb.develfoodspringweb.models.StatusRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusPresent {

    /**
     * Created by Luis Gregorio.
     *
     * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
     */
    private Long id;
    private StatusRequest status = StatusRequest.WAITING_TO_ACCEPT;
    private LocalDateTime dateRequest = LocalDateTime.now();
}
