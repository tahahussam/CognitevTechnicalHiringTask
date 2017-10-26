package com.cogn.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

import com.cogn.config.SecurityConfiguration;
import com.cogn.controller.UserController;

@ComponentScan(basePackages = { "com.cogn.service" })
@SpringBootApplication
public class CognitevApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CognitevApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CognitevApplication.class);
	}

	@Bean
	public SecurityConfiguration securityConfiguration() {
		return new SecurityConfiguration();
	}

	@Autowired
	DataSource dataSource;

	@Bean
	public UserController UserController() {
		return new UserController();
	}
}