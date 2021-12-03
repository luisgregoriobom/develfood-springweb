package br.com.develfoodspringweb.develfoodspringweb.controller.userCommon;

import lombok.Data;

/**
 * Created by Luis Gregorio.
 *
 * Present class created to simplify the JSON response body, also simplifying the information sent to the frontend
 */
@Data
public class UserPresentUser {
    
    private String address;
    private String phone;
    private String photo;
}
