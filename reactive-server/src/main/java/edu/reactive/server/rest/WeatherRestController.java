package edu.reactive.server.rest;

import edu.reactive.server.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}/from/{startDate}/to/{endDate}")
    public Flux<CityWeatherResponse> cityWeatherBetweenDates(@PathVariable String city, @PathVariable String startDate, @PathVariable String endDate) {
        log.info("displaying weather for city={}, between {} and {}", URLDecoder.decode(city, StandardCharsets.UTF_8), startDate, endDate);
        return weatherService.cityWeatherBetweenDates(city, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @GetMapping("/{city}")
    public Flux<CityWeatherResponse> cityWeather(@PathVariable String city) {
        log.info("displaying weather for city={}", URLDecoder.decode(city, StandardCharsets.UTF_8));
        return weatherService.cityWeather(city);
    }
}
