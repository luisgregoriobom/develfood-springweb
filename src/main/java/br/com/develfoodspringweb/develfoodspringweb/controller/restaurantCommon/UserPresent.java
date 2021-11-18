package br.com.develfoodspringweb.develfoodspringweb.controller.restaurantCommon;

import lombok.Data;

/**
 * Created by Luis Gregorio.
 *
 * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
 */
@Data
public class UserPresent {

    private String name;
    private String cpf;
    private String email;
    private String address;
    private String phone;
}
