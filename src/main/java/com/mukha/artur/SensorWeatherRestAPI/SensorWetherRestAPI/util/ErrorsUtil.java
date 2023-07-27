package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorMsg=new StringBuilder();
        List<FieldError> fieldErrors=bindingResult.getFieldErrors();

        for (FieldError fieldError:fieldErrors){
errorMsg.append(fieldError.getField()).
        append(" - ").
        append(fieldError.getDefaultMessage()==null? fieldError.getCode():fieldError.getDefaultMessage()).
        append(";");
        }
        throw new MeasurementException(errorMsg.toString());
    }
}
