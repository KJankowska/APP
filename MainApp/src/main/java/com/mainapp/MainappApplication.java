package com.mainapp;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@SpringBootApplication
@Configuration
public class MainappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainappApplication.class, args);
	}

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        return builder.modulesToInstall(new JavaTimeModule());
    }

    @Bean
    public Client client() {
	    return ClientBuilder.newClient();
    }
}
