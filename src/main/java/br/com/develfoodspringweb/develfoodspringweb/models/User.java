package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Transactional
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String password;
    private String email;
    private String address;
    private String phone;
    private LocalDateTime registrationDate = LocalDateTime.now();
//    private String registrationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    @Column(columnDefinition = "text")
    private String photo;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Request> request;
    @OneToMany(mappedBy = "user")
    private List<Profile> userProfile = new ArrayList<>();

    public User(String name, String cpf, String password, String email, String address, String phone, String photo, LocalDateTime registrationDate) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.registrationDate = registrationDate;
    }

    public User(UserForm userForm){
        this.name = userForm.getName();
        this.cpf = userForm.getCpf();
        this.email = userForm.getEmail();
        this.address = userForm.getAddress();
        this.phone = userForm.getPhone();
        this.photo = userForm.getPhoto();
    }

    /**
     * Permission methods for user access to authenticate in the system.
     * For SpringSecurity, in addition to the User, we need to have a class to represent,
     *the profile related to User permissions.
     *
     * Profile is an entity, there must be Cardinality from User to Profile,
     *user can have multiple Profiles, and Profile can be linked to multiple users.
     *
     * Implemented override methods of the UserDetails interface.
     *
     * @author: Luis Gregorio
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userProfile;
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

