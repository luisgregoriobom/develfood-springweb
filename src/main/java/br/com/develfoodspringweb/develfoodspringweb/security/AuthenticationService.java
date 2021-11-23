package br.com.develfoodspringweb.develfoodspringweb.security;


import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Class validates user email and password with database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements UserDetailsService {

    private final UserRepository repository;
    private final RestaurantRepository restaurantRepository;

    /**
     *Authentication Logic that will look for the email in the database, if it doesn't find it returns an exception.
     * @param name
     * @return
     * @throws UsernameNotFoundException
     * @author: Luis Gregorio
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(name);
        if (user.isPresent()) {
            return user.get();
        }
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail(name);
        if (restaurant.isPresent()) {
            return restaurant.get();
        }
        throw new UsernameNotFoundException("Dados Inválidos");
    }
}
