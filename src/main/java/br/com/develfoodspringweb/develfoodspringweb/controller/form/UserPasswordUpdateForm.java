package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class UserPasswordUpdateForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String password;

    /**
     * Method to call User data update.
     * @param id
     * @param userRepository
     * @return
     * @author: Luis Gregorio
     */
    public User updatePassword(Long id, UserRepository userRepository) {
        User user = userRepository.getById(id);
        user.setPassword(this.password);

        return user;
    }

    /**
     * Function to convert the object Form Class received into a Model Object
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    public User convertToUserUpdate(UserPasswordUpdateForm form) {
        return new User();
    }
}
