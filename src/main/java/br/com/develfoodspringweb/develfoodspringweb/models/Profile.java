package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Luis Gregorio.
 * Class created to return the profile of the user being authenticated
 */
@Entity
@Data
public class Profile implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private User user;
    private Restaurant restaurant;

    /**
     * Returns a method over written attribute that has the name of the profile
     * @return
     * @author: Luis Gregorio
     */
    @Override
    public String getAuthority() {
        return name;
    }
}
