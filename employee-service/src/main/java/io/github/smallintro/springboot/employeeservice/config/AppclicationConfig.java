package io.github.smallintro.springboot.employeeservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppclicationConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
