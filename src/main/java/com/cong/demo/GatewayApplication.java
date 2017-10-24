package com.cong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.WebApplicationInitializer;

import com.cong.config.SecurityConfiguration;
import com.cong.controller.GatewayController;


@PropertySources({ @PropertySource(value = "file:C:/session/zuul.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${user.home}/session/zuul.properties", ignoreResourceNotFound = true) })
@SpringBootApplication
public class GatewayApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GatewayApplication.class);
	}

	@Bean
	public GatewayController accessTokenController() {
		return new GatewayController();
	}

	@Bean
	public SecurityConfiguration securityConfiguration() {
		return new SecurityConfiguration();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("OPTIONS");
//		config.addAllowedMethod("HEAD");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("PUT");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("DELETE");
//		config.addAllowedMethod("PATCH");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}
}