package com.microservice.passwordmanagerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.microservice")
public class PasswordmanagerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordmanagerserviceApplication.class, args);
	}

}
