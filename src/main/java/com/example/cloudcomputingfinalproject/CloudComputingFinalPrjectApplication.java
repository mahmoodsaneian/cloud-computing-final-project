package com.example.cloudcomputingfinalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;


@SpringBootApplication
public class CloudComputingFinalPrjectApplication implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(CloudComputingFinalPrjectApplication.class, args);
	}

	@Override
	public void run(String... args) {

	}
}
