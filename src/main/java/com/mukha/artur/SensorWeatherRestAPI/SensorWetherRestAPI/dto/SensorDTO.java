package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Measurement;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class SensorDTO {
    @Size(min = 2, max = 30, message = "Имя датчика должно быть от 2 до 30 символов")
    @NotEmpty
    private String name;



    public SensorDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
