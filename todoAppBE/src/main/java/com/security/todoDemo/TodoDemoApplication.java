package com.security.todoDemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class TodoDemoApplication {

	@Value("#{'${allowedOrigins:*}'.split(',')}")

	private List<String> allowedOrigins;

	public static void main(String[] args) {
		SpringApplication.run(TodoDemoApplication.class, args);
	}

		// Enable CORS
		
	@Bean

	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);

		config.setAllowedOrigins(allowedOrigins);

		config.addAllowedHeader("*");

		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/*/**", config);

		return new CorsFilter(source);

	}
}




