package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Measurement;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSensor() == null) {
            return;
        }

        if (sensorService.getSensorByName(measurement.getSensor().getName()).isEmpty())
        errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем!");

        if (measurement.getValue()>100&&measurement.getValue()<-100)
            errors.rejectValue("value","Диапозон значений должен быть от -100 до 100");

    }
}
