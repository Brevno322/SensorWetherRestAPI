package com.mukha.artur.SensorWeatherRestAPI.SensorWetherRestAPI.util;

public class MeasurementErrorResponse {
    private String massage;
    private Long timestamp;

    public MeasurementErrorResponse(String massage, Long timestamp) {
        this.massage = massage;
        this.timestamp = timestamp;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
