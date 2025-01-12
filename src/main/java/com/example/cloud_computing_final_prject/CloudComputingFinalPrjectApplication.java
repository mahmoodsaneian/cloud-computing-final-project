package com.example.cloud_computing_final_prject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.sql.Connection;

@EnableJpaRepositories(basePackages = "com.example.cloud_computing_final_prject.repository")
@SpringBootApplication
@EntityScan(basePackages = "com.example.cloud_computing_final_prject.data")
public class CloudComputingFinalPrjectApplication implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(CloudComputingFinalPrjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("Database connection successful!");
		} catch (Exception e) {
			System.err.println("Database connection failed: " + e.getMessage());
		}
	}
}
