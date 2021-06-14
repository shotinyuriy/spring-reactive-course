package edu.reactive.server.rest;

import edu.reactive.server.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}/from/{startDate}/to/{endDate}")
    public Flux<CityWeatherResponse> cityWeatherBetweenDates(@PathVariable String city, @PathVariable String startDate, @PathVariable String endDate) {
        System.out.println("city="+city);
        return weatherService.cityWeatherBetweenDates(city, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @GetMapping("/{city}")
    public Flux<CityWeatherResponse> cityWeather(@PathVariable String city) {
        System.out.println("city="+city);
        return weatherService.cityWeather(city);
    }
}
