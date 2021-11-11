package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantNameRepository extends JpaRepository<Restaurant, Long> {

    //classe para buscar a entidade Restaurante como String

    Optional<Restaurant> findById(Long id);
}
