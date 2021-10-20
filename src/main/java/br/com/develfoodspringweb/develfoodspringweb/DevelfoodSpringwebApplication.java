package br.com.develfoodspringweb.develfoodspringweb;

import br.com.develfoodspringweb.develfoodspringweb.configuration.InitialConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DevelfoodSpringwebApplication implements CommandLineRunner{

	@Autowired
	private InitialConfig initialConfig;

	public static void main(String[] args) {
		SpringApplication.run(DevelfoodSpringwebApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		initialConfig.configurar();

	}
}