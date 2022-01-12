package br.com.develfoodspringweb.develfoodspringweb.controller.userCommon;

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
public class RequestPresentUser {

    private Long id;
    private String dateRequest;
    private StatusRequest status;
    private List<PlatePresentUser> plates;
    private UserPresentUser user;
}
