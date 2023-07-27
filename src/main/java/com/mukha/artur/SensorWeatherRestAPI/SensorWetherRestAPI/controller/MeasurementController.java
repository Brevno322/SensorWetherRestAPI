package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.controller;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto.MeasurementDTO;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Measurement;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Sensor;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services.MeasurementService;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.services.SensorService;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.ErrorsUtil;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.MeasurementErrorResponse;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.MeasurementException;
import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    private final MeasurementValidator measurementValidator;
    private final SensorService sensorService;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator, SensorService sensorService) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.sensorService = sensorService;
    }


    @GetMapping()
    public List<MeasurementDTO> index() {
        return measurementService.getAll();
    }

    @GetMapping("/rainyDaysCount")
    public List<MeasurementDTO> getRainyDaysCount() {
        return measurementService.RainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()){
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
                measurementService.save(measurement);
                return ResponseEntity.ok(HttpStatus.OK);

    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
