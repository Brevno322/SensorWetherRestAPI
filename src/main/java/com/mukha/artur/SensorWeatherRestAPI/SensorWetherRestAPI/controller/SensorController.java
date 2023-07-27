package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.controller;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto.SensorDTO;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Sensor;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services.SensorService;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.ErrorsUtil;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.MeasurementErrorResponse;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.MeasurementException;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.form.ErrorsTag;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;
    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO
            , BindingResult bindingResult) {
        Sensor sensor=convertToSensor(sensorDTO);
        sensorValidator.validate(sensor,bindingResult);
        if (bindingResult.hasErrors()){
           ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
