package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.dto;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MeasurementDTO {
    @Min(-100)
    @Max(100)
    @Size(groups = Double.class , message = "Данные температуры должны быть в диапозоне от -100 до 100 градусов по Цельсию")
    @NotEmpty(groups = Double.class,message = "Температура не должна быть пустым")
    private Double value;

    @NotEmpty(groups = Boolean.class,message = "Показатель дождя не должен быть пстым")
    private Boolean raining;

    private Sensor sensor;

    public MeasurementDTO() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
