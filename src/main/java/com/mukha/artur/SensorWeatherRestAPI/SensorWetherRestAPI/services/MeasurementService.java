package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services;


import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto.MeasurementDTO;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Measurement;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.repositories.MeasurementRepository;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }


    public List<MeasurementDTO> getAll() {
        return measurementRepository.findAll().
                stream().
                map(this::convertToMeasurementDTO).
                collect(Collectors.toList());
    }

    public List<MeasurementDTO> RainyDaysCount() {
        return measurementRepository.findMeasurementByRainingIsTrue()
                .stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());

    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }



    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorService.getSensorByName(measurement.getSensor().getName()).get());
        measurement.setLocalDateTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }
}
