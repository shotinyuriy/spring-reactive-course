package edu.reactive.server.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CityWeatherResponse {
    private String city;
    private LocalDateTime dateTime;
    private Integer temperature;
}
