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
    @JsonBackReference("passwordRestaurant")
    private String password;
    private String email;
    private String address;
    private String phone;
    private String foodType;

    @Column(columnDefinition = "text")
    private String photo;

    @OneToMany(mappedBy = "restaurantName")
    @JsonIgnore
    private List<Restaurant> restaurantName = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Plate> plates;
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore 
    private List<Profile> restaurantProfile = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant")
    private List<Request> requests;

    public Restaurant(String name, String cnpj, String password, String email, String address, String phone, String foodType, List plates, String photo) {
        this.name = name;
        this.cnpj = cnpj;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.foodType = foodType;
        this.plates = plates;
        this.photo = photo;
    }

    public Restaurant(RestaurantForm restaurantForm){
        this.name = restaurantForm.getName();
        this.cnpj = restaurantForm.getCnpj();
        this.email = restaurantForm.getEmail();
        this.address = restaurantForm.getAddress();
        this.phone = restaurantForm.getPhone();
        this.photo = restaurantForm.getPhoto();
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


