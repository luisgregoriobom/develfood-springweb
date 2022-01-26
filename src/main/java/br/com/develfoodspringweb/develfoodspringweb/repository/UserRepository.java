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

    public static Specification<User> userMonthlyBirthday (){
            return (root, query, criteriaBuilder) -> {
                criteriaBuilder.equal(root.get("")),
            }

        }

    @Query("from User u where month(u.registrationDate) = month(CURRENT_DATE)")
    List<User> findByRegistrationDate(LocalDateTime today);
}
