package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.repositories;

import com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Optional<Sensor> findByName(String name);
}
