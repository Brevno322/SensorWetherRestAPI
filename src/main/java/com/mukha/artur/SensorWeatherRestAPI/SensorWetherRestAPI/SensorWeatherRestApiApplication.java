package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SensorWeatherRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorWeatherRestApiApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
