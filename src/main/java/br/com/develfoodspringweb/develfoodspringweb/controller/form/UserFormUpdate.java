package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Luis Gregorio.
 *
 * In this class we can define what data a user can update in the system.
 */
@Data
public class UserFormUpdate {

    @NotNull @NotEmpty @Length(min = 5)
    private String password;
    @NotNull @NotEmpty @Length(min = 5)
    private String address;
    @NotNull @NotEmpty @Length(min = 11)
    private String phone;

    /**
     * Method to call User data update.
     * @param id
     * @param userRepository
     * @return
     * @author: Luis Gregorio
     */
    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getById(id);
        user.setPassword(this.password);
        user.setAddress(this.address);
        user.setPhone(this.phone);
        return user;
    }
}
