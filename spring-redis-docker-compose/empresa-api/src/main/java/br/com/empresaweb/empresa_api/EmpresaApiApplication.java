package br.com.empresaweb.empresa_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmpresaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpresaApiApplication.class, args);
	}

}
