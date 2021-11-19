package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Transactional
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String login;
    private String password;
    private String email;
    private String address;
    private String phone;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Request> request;



    public User(String name, String cpf, String login, String password, String email, String address, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }


    public User(UserForm userForm){
        this.name = userForm.getName();
        this.cpf = userForm.getCpf();
        this.login = userForm.getLogin();
        this.email = userForm.getEmail();
        this.address = userForm.getAddress();
        this.phone = userForm.getPhone();
    }

    ////////////////////MÉTODOS DE PERMISSÃO PARA ACESSO DO USUARIO AUTENTICAR NO SISTEMA///////////////////////

    @ManyToMany
    private List<Profile> userProfile = new ArrayList<>();

    // Pro SpringSecurity, além do User, precisamos ter uma classe pra representar
    // o Perfil relacionado com as permissões do User

    //Como Profile é uma entidade, tem de haver Cardinalidade de User para Profile
    //User pode ter varios Profiles e Profiles pode estar atrelado a vários Users

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

