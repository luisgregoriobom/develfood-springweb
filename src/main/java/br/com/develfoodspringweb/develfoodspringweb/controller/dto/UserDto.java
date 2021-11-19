package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String cpf;
    private String login;
    private String email;
    private String address;
    private String phone;
    private List<Request> request;


    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param users
     * @return
     * @author: Thomas B.P.
     */
    public static List<UserDto> converter(List<User> users) {
    return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}