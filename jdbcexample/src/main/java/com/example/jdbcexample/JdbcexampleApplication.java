package com.example.jdbcexample;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableJSONDoc
@EnableConfigurationProperties
public class JdbcexampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(JdbcexampleApplication.class, args);
	}
}
