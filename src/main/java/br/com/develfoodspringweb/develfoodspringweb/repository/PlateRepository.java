package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Interface created to find name of plates through the implemented methods.
 */
public interface PlateRepository extends JpaRepository<Plate, Long>{


    /**
     * Function to search for a plate by name in PlateService, getPlateByName.
     * @param name
     * @return
     * @author: Luis Gregorio
     */
    Optional<Plate> findByName(String name);

    /**
     * Function to search for a plate by name in GET Method List.
     * @param plateName
     * @return
     * @author: Luis Gregorio
     */
    List<Plate> findByPlateName(String plateName);

}
