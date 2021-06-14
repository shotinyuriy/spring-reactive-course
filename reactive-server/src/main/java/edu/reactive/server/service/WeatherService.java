package edu.reactive.server.service;

import edu.reactive.server.converter.WeatherDataToCityWeatherResponseConverter;
import edu.reactive.server.repository.WeatherDataRepository;
import edu.reactive.server.rest.CityWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private WeatherDataToCityWeatherResponseConverter converter;

    public Flux<CityWeatherResponse> cityWeather(String city) {
        return weatherDataRepository.findAll()
                .filter(weatherData -> weatherData.getCity().equals(city))
                .map(converter::convert);
    }

    public Flux<CityWeatherResponse> cityWeatherBetweenDates(String city, LocalDate startDate, LocalDate endDate) {
        LocalDateTime offsetStartDate = startDate.atTime(0,0);
        LocalDateTime offsetEndDate = endDate.atTime(23,59, 59, 999999);

        return weatherDataRepository.findAll()
                .filter(weatherData -> weatherData.getCity().equals(city)
                        && (weatherData.getDateTime().isEqual(offsetStartDate) || weatherData.getDateTime().isAfter(offsetStartDate))
                        && (weatherData.getDateTime().isEqual(offsetEndDate)|| weatherData.getDateTime().isAfter(offsetEndDate))
                )
                .map(converter::convert);

    }
}
