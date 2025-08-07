package com.ngbilling.gestao_bancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.ngbilling.gestao_bancaria",
		excludeFilters = @ComponentScan.Filter(
				type = FilterType.REGEX,
				pattern = "com\\.ngbilling\\.gestao_bancaria\\.core\\..*"
		)
)
@EnableJpaRepositories(basePackages = "com.ngbilling.gestao_bancaria.infrastructure.repository")
@EntityScan(basePackages = "com.ngbilling.gestao_bancaria.infrastructure.persistence")
public class GestaoBancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoBancariaApplication.class, args);
	}

}
