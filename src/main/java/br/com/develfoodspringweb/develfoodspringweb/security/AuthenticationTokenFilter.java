package br.com.develfoodspringweb.develfoodspringweb.security;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by Luis Gregorio.
 *
 * class will intercept token request to grant user access.
 */
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenServ tokenServs;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public AuthenticationTokenFilter(TokenServ tokenServs, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.tokenServs = tokenServs;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Authentication by Token.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     * @author: Luis Gregorio
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recoveryToken(request);
        boolean valid = tokenServs.isTokenValid(token);
        if(valid) {
            String userType = tokenServs.getUserType(token);
            if(userType.equals("user")) {
                authenticateCliente(token);
            } else if(userType.equals("restaurant")) {
                authenticateRestaurant(token);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Method to authenticate User
     * @param token
     * @author: Luis Gregorio
     */
    private void authenticateCliente(String token) {

        Long idUser = tokenServs.getIdUser(token);
        User users = userRepository.getById(idUser);
        System.out.println(users.getId());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Method to authenticate Restaurant
     * @param token
     * @author: Luis Gregorio
     */
    private void authenticateRestaurant(String token){
        Long idRestaurant = tokenServs.getIdRestaurant(token);
        Restaurant restaurant = restaurantRepository.findById(idRestaurant).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(restaurant, null, restaurant.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    /**
     * Method will cast a null if the token header does not meet any of these requirements.
     * @param request
     * @return
     * @author: Luis Gregorio
     */
    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
