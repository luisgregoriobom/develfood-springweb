package br.com.develfoodspringweb.develfoodspringweb.security;

import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Luis Gregorio.
 *
 * Class that configures authentication, authorization and endpoints
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;
    private final TokenServ tokenService;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Override Method that creates the AuthenticationManager object
     * @return
     * @throws Exception
     * @author: Luis Gregorio
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Method for configuring authentication. Makes a call to the Authentication Service, a class that contains the authentication logic.
     * @param auth
     * @throws Exception
     * @author: Luis Gregorio
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());

    }

    /**
     * Method for configuring authorization. Allows access to endpoints.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/user").permitAll()
                .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/restaurant").permitAll()
                .antMatchers(HttpMethod.POST,"/api/restaurant").permitAll()
                .antMatchers(HttpMethod.GET,"/api/restaurant/*").permitAll()
                .antMatchers(HttpMethod.POST,"/api/restaurant/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/plate").permitAll()
                .antMatchers(HttpMethod.GET,"/api/plate/*").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth").permitAll()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().sameOrigin()
                .and().cors().and().csrf().disable().authorizeRequests()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationTokenFilter(tokenService, userRepository, restaurantRepository), UsernamePasswordAuthenticationFilter.class);
    }
}


