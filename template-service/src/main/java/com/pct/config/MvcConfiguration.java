package com.pct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
public class MvcConfiguration {

	/**
	 * Jackson builder.
	 *
	 * @return the jackson 2 object mapper builder
	 */
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		b.failOnEmptyBeans(false);
		return b;
	}
}
