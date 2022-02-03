package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Interface created to find e-mail and names of users through the implemented methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

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


    /**
     * HQL query to find the monthly birthday users of registration
     * @param today
     * @return
     * @author: Thomas Benetti
     */
    @Query("FROM User u WHERE day(u.registrationDate) = day(CURRENT_DATE) AND " +
            "month(u.registrationDate) != month(CURRENT_DATE)")
    List<User> findMonthlyBirthdayUsers(LocalDateTime today);

    /**
     * HQL query to find users who have been registered for one month
     * @param today
     * @return
     * @author: Thomas Benetti
     */
    @Query("FROM User u WHERE day(CURRENT_DATE - 30) = day(u.registrationDate)")
    List<User> findOneMonthBirthdayUsers(LocalDateTime today);

}
