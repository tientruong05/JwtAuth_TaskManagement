package com.TaskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class and entry point for the Task Management Spring Boot application.
 *
 * The {@code @SpringBootApplication} annotation enables key Spring Boot features,
 * including auto-configuration, component scanning, and Spring configuration.
 */
@SpringBootApplication
public class TaskManagementApplication {

	/**
	 * The main method, which serves as the entry point for running the application.
	 *
	 * @param args Command-line arguments that can be passed on application startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
