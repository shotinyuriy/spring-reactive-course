package edu.reactive.client.service;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CityWeatherResponse {
    private String city;
    private LocalDateTime dateTime;
    private Integer temperature;
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
