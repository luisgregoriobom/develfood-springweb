package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Luis Gregorio
 *
 * Repository created to use the find query in the service.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
