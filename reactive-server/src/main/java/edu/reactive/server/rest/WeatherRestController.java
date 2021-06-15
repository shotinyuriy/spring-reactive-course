package edu.reactive.server.rest;

import edu.reactive.server.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static log.util.LogUtil.encode;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> cities() {
        return weatherService.cities();
    }

    @GetMapping(value = "/{city}/from/{startDate}/to/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CityWeatherResponse> cityWeatherBetweenDates(@PathVariable String city, @PathVariable String startDate, @PathVariable String endDate) {
        log.info("displaying weather for city={}, between {} and {}", encode(city), startDate, endDate);
        return weatherService.cityWeatherBetweenDates(city, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CityWeatherResponse> cityWeather(@PathVariable String city) {
        log.info("displaying weather for city={}", encode(city));
        return weatherService.cityWeather(city);
    }
}
