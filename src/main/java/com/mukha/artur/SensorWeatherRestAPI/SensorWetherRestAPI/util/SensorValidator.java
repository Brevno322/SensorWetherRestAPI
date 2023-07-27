package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto.SensorDTO;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Sensor;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
       if (sensorService.getSensorByName(sensor.getName()).isPresent()){
           errors.rejectValue("name","","Сенсор с таким именем уже существет");
       }


    }
}
