package edu.reactive.client.view;

import edu.reactive.client.service.dto.CityWeatherResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.SortedSet;

@AllArgsConstructor
@Getter
public class WeatherOnDate {
    LocalDate localDate;
    SortedSet<CityWeatherResponse> cityWeatherOnDate;
}
