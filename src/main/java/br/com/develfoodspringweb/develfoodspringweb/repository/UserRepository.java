package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Interface created to find e-mail and names of users through the implemented methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Function to search for a user by name.
     * @param nameUser
     * @return
     * @author: Luis Gregorio
     */
    Optional<User> findByName(String nameUser);

    /**
     * Function to search for a user by email.
     * @param email
     * @return
     * @author: Luis Gregorio
     */
    Optional<User> findByEmail(String email);
}
