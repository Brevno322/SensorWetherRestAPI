package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(-100)
    @Max(100)
    @Size(groups = Double.class, message = "Данные температуры должны быть в диапозоне от -100 до 100 градусов по Цельсию")
    @NotEmpty(groups = Double.class,message = "Температура не должна быть пустым")
    private Double value;

    @Column(name = "raining")
    @NotEmpty(groups = Boolean.class,message = "Показатель дождя не должен быть пстым")
    private Boolean raining;

    @Column(name = "data")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "id_sensor",referencedColumnName = "id")
    @NotNull
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(Double value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
