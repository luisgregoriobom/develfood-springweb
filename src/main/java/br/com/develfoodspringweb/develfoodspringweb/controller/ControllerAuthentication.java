package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.TokenDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.LoginForm;
import br.com.develfoodspringweb.develfoodspringweb.security.TokenServ;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Luis Gregorio.
 * 
 * Class generated to implement the Controller that authenticates email and password.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerAuthentication {

    private final AuthenticationManager authManager;
    private final TokenServ tokenServ;

    /**
     * That Controller authenticates email and password in the DataBase and call the AuthenticationService class.
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginInformation = form.converter();
        try {
            Authentication authentication = authManager.authenticate(loginInformation);
            String token = tokenServ.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
