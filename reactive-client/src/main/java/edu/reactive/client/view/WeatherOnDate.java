package edu.reactive.client.view;

import edu.reactive.client.service.CityWeatherResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class WeatherOnDate {
    LocalDate localDate;
    List<CityWeatherResponse> cityWeatherOnDate;
}
