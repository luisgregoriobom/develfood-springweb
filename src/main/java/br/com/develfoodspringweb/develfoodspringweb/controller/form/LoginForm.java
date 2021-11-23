package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by Luis Gregorio.
 *
 * The class has the function of getting the user's email and password to authenticate.
 */
@Data
public class LoginForm {

    private String email;
    private String password;

    /**
     * Login information to authenticate and generate the token.
     * @return
     * @author: Luis Gregorio
     */
    public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(email, password);
    }
}
