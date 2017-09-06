package com.ebsco.training.bookmiddle.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class which registers beans to be used for Jackson dependency injection and configuration.
 * https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring
 * @author kylebober
 *
 */
@Configuration
public class JacksonConfig {
    
	@Bean
	public Module parameterNamesModule() {
	  return new ParameterNamesModule();
	}
	
	@Bean
	public Module jdk8Module() {
	  return new Jdk8Module();
	}
	
	@Bean
	public Module javaTimeModule() {
	  return new JavaTimeModule();
	}
}
