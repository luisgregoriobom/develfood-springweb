package br.com.develfoodspringweb.develfoodspringweb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DevelfoodSpringwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelfoodSpringwebApplication.class, args);
	}
}
