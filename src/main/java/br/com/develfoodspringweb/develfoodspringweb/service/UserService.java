package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserPasswordUpdateForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    /**
     * Function that make a query with the name of the user as parameter and check in the database if the name is present
     * @param nameUser
     * @return
     * @author: Thomas B.P.
     */
    public UserDto getUserByName(String nameUser){
        Optional<User> opt = userRepository.findByName(nameUser);
        if (!opt.isPresent()){
            return null;
        }
        return UserDto.convertToUserDto(opt.get());
    }

    /**
     * Function to register new User
     * @param userForm
     * @return
     * @author: Thomas B.P.
     */
    public UserDto register(UserForm userForm){
        User user = userForm.convertToUser(userForm);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            String photo = user.getPhoto();
            String encodedPassword = passwordEncoder.encode(userForm.getPassword());
            user.setPassword(encodedPassword);
            user.setPhoto(photo);
        } catch (Exception e) {
            return null;
        }
        userRepository.save(user);
        if (user.getId() == null) {
            return null;
        }
        return new UserDto(user);
    }

    /**
     * Function to detail a User information
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public UserDto details(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        return new UserDto(user.get());
    }

    /**
     * Function to Update a User data with encrypt code.
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    public UserDto update(UserFormUpdate form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        Optional<User> userOpt = userRepository.findByEmail(userAuth);
        Long userId = userOpt.get().getId();

        if (!userOpt.isPresent()) {
            return null;

        }
        User user = form.update(userId, userRepository);
        return new UserDto(user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhone(),
                user.getPhoto());
    }

    /**
     * Function to update only the password from a user.
     * @param passwordUpdateForm
     * @return
     * @author: Thomas B.P.
     */
    public UserDto updatePassword(UserPasswordUpdateForm passwordUpdateForm){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        Optional<User> userOpt = userRepository.findByEmail(userAuth);
        Long userId = userOpt.get().getId();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try{
            String encodedPassword = passwordEncoder.encode(passwordUpdateForm.getPassword());
            passwordUpdateForm.setPassword(encodedPassword);
        } catch (Exception e){
            return null;
        }
        if (!userOpt.isPresent()){
            return null;
        }
        User user = passwordUpdateForm.updatePassword(userId, userRepository);
        return new UserDto(user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhone(),
                user.getPhoto());
    }

    /**
     * Function to remove a User
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    public UserDto remove(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return new UserDto(user.get());
        }
        return null;
    }

}
