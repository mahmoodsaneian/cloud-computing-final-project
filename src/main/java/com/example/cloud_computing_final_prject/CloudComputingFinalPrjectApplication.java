package com.example.cloud_computing_final_prject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.cloud_computing_final_prject.repository")
@SpringBootApplication
public class CloudComputingFinalPrjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudComputingFinalPrjectApplication.class, args);
	}

}
