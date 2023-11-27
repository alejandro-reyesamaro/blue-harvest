package com.harvest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages={
	"com.harvest.api",
	"com.harvest.api.controllers.strategies",
	"com.harvest.core",
	"com.harvest.application",
	"com.harvest.infrastructure",
})
@EnableTransactionManagement
@EntityScan("com.harvest.infrastructure.repository.*")
@EnableJpaRepositories("com.harvest.infrastructure.repository")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
