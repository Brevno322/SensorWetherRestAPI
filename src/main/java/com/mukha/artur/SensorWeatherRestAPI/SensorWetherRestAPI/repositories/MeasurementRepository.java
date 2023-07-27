package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.repositories;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    List<Measurement> findMeasurementByRainingIsTrue();
}
