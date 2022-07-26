package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
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
    private String address;
    @NotNull @NotEmpty @Length(min = 11)
    private String phone;
    @Column(columnDefinition = "text")
    private String photo;

    /**
     * Method to call User data update.
     * @param id
     * @param userRepository
     * @return
     * @author: Luis Gregorio
     */
    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getById(id);
        user.setAddress(this.address);
        user.setPhone(this.phone);
        user.setPhoto(this.photo);
        return user;
    }

    /**
     * Function to convert the object Form Class received into a Model Object
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    public User convertToUserUpdate(UserFormUpdate form) {
        return new User();
    }
}
