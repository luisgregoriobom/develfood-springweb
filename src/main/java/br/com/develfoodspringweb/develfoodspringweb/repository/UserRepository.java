package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
    //fazendo a query pela linguagem do springdata 'findByPedidoPreco' de um atributo de uma classe relacionada
    //nesse caso, a classe relacionada à Usuario é a Pedido e o atributo é preco (que vem da classe Pedido)
//    User findByName(String nameUser); NÃO UTILIZADO porque retorna retorna NullPointerException para parametro que não encontrado no banco

    Optional<User> findByName(String nameUser);

}
