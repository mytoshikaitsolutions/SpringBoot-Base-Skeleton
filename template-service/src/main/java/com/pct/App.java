package com.pct;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class App {

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
