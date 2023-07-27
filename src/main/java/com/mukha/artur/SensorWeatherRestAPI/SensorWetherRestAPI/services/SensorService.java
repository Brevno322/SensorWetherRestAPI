package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto.SensorDTO;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Sensor;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;


    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
    }


    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);

    }




}
