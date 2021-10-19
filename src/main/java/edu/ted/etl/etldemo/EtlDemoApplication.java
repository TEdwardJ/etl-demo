package edu.ted.etl.etldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EtlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtlDemoApplication.class, args);
	}

}
