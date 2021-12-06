package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    private String login;
    @JsonBackReference("passwordRestaurant")
    private String password;
    private String email;
    private String address;
    private String phone;
    private String foodType;

    @OneToMany(mappedBy = "restaurantName")
    @JsonIgnore
    private List<Restaurant> restaurantName = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Plate> plates;
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore 
    private List<Profile> restaurantProfile = new ArrayList<>();

    public Restaurant(String name, String cnpj, String login, String password, String email, String address, String phone, String foodType, List plates) {
        this.name = name;
        this.cnpj = cnpj;
        this.login = login;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.foodType = foodType;
        this.plates = plates;
    }

    public Restaurant(RestaurantForm restaurantForm){
        this.name = restaurantForm.getName();
        this.cnpj = restaurantForm.getCnpj();
        this.login = restaurantForm.getLogin();
        this.email = restaurantForm.getEmail();
        this.address = restaurantForm.getAddress();
        this.phone = restaurantForm.getPhone();
    }

    /**
     * Constructor created just to manually add data into the database with configurar method that belong to InitialConfig class on configuration package
     * @param name
     * @param phone
     * @author: Thomas B.P.
     */
    public Restaurant(String name, String cnpj, String phone, String foodType){
        this.name = name;
        this.cnpj = cnpj;
        this.phone = phone;
        this.foodType = foodType;
    }

    /**
     * Permission methods for user access to authenticate in the system
     * For SpringSecurity, in addition to the Restaurant, we need to have a class to represent,
     *the profile related to Restaurant permissions.
     *
     * Profile is an entity, there must be Cardinality from Restaurant to Profile,
     *restaurant can have multiple Profiles, and Profile can be linked to multiple Restaurants.
     *
     * Implemented methods of the UserDetails interface
     *
     * @author: Luis Gregorio
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.restaurantProfile;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


