package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String address;
    private String phone;
    private List<Request> request;
    private String photo;
    private LocalDateTime registrationDate;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.photo = user.getPhoto();
        this.registrationDate = user.getRegistrationDate();
    }

    /**
     * Constructor to return specifics atributes to update and updatePassword functions
     * @param name
     * @param email
     * @param address
     * @param phone
     * @param photo
     * @author: Thomas B.P.
     */
    public UserDto(String name, String email, String address, String phone, String photo){
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
    }

    /**
     * Constructor to return only users email
     * @param email
     * @author: Thomas Benetti
     */
    public UserDto(String email){
        this.email = email;
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param user
     * @return
     * @author: Thomas B.P.
     */
    public static UserDto convertToUserDto(User user) {
    return new UserDto(user);
    }

    /**
     * Function to convert list of model object to a list of DTO objects
     * @param users
     * @return
     * @author: Thomas Benetti
     */
    public static List<UserDto> convertToListDto(List<User> users){
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}