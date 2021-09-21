package com.mytoshika.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("com.mytoshika"))              
				.paths(Predicate.not(PathSelectors.regex("(/error)")))                         
				.build();                                           
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Employee APIs")
				.description("Employee API reference for developers")
				.termsOfServiceUrl("http://Employee.com")
				.license("Employee License")
				.licenseUrl("employeeabc@gmail.com")
				.version("1.0")
				.build();
	}
}
