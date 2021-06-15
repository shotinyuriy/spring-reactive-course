package edu.reactive.client.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CityWeatherResponse implements Comparable<CityWeatherResponse> {
    private String city;
    private LocalDateTime dateTime;
    private Integer temperature;
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public int compareTo(CityWeatherResponse o) {
        return this.getTime().compareTo(o.getTime());
    }
}
