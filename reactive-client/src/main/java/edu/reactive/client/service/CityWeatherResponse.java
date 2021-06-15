package edu.reactive.client.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityWeatherResponse {
    private String city;
    private LocalDateTime dateTime;
    private Integer temperature;
}
