package io.github.aslary.jwtcrudstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JwtCrudStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtCrudStarterApplication.class, args);
	}

}
