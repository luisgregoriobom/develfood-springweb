package br.com.develfoodspringweb.develfoodspringweb.security;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Luis Gregorio.
 *
 * This class is responsible for taking the database,
 * authentication and generating a token for the registered user.
 */
@Service
public class TokenServ {


    @Value("${develfoodspringweb.jwt.secret}")
    private String secret;

    @Value("${develfoodspringweb.jwt.expiration}")
    private String expiration;

    /**
     * In this method the API will create the token.
     * @param authentication
     * @return
     * @author: Luis Gregorio
     */
    public String generateToken(Authentication authentication) {

        Object resultadoAuthentication = authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        if (resultadoAuthentication instanceof User) {
            User logged = (User) resultadoAuthentication;
            return Jwts.builder()
                    .setIssuer("API da Develfood")
                    .setSubject(logged.getId().toString())
                    .setIssuedAt(today)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .claim("type", "user")
                    .compact();
        }
        if (resultadoAuthentication instanceof Restaurant) {
            Restaurant restaurantLogged = (Restaurant) authentication.getPrincipal();
            return Jwts.builder()
                    .setIssuer("API da Develfood")
                    .setSubject(restaurantLogged.getId().toString())
                    .setIssuedAt(today)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .claim("type", "restaurant")
                    .compact();
        }
        throw new UsernameNotFoundException("Dados digitados não encontrados!");
    }

    /**
     * Method to valid the Token
     * @param token
     * @return
     * @author: Luis Gregorio
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method that searches the User by ID to authenticate it with the token.
     * @param token
     * @return
     * @author: Luis Gregorio
     */
    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * Method that searches the Restaurant by ID to authenticate it with the token
     * @param token
     * @return
     * @author: Luis Gregorio
     */
    public Long getIdRestaurant(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * Method created to use the word "type", this function will be useful when identifying if the authenticated access will be from a user or restaurant.
     * @param token
     * @return
     * @author: Luis Gregorio
     */
    public String getUserType(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return (String) claims.get("type");
    }
}
