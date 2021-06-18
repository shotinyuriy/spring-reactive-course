package edu.reactive.server.rest;

import edu.reactive.server.rest.dto.*;
import edu.reactive.server.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CityResponse> cities() {
        return weatherService.cities();
    }

    @GetMapping(value = "/{city}/from/{startDate}/to/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CityWeatherResponse> cityWeatherBetweenDates(@PathVariable String city, @PathVariable String startDate, @PathVariable String endDate) {
        log.info("displaying weather for city={}, between {} and {}", city, startDate, endDate);
        return weatherService.cityWeatherBetweenDates(city, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CityWeatherResponse> cityWeather(@PathVariable String city) {
        log.info("displaying weather for city={}", city);
        return weatherService.cityWeather(city);
    }

    @Validated
    @PostMapping(value = "/cities")
    public Mono<CreateCityResponse> createCity(@Valid @RequestBody Mono<CreateCityRequest> requestMono) {
        return requestMono
                .flatMap(request ->
                        weatherService.createCityAndGenerateWeatherData(request.getCity(), request.getWeatherDataCount()))
                .map(createResult -> new CreateCityResponse(createResult));
    }

    @Validated
    @PostMapping(value = "/data")
    public Mono<CreateWeatherDataResponse> createWeatherData(@Valid @RequestBody Mono<CreateWeatherDataRequest> requestMono) {
        return requestMono
                .flatMap(request ->
                        weatherService.createWeatherData(
                                request.getId(),
                                request.getCity(),
                                request.getDate().atTime(request.getHour(), 0),
                                request.getTemperature())
                ).map(weatherData -> {
                    var response = new CreateWeatherDataResponse();
                    response.setId(weatherData.getId());
                    response.setCity(weatherData.getCity());
                    response.setDate(weatherData.getDateTime().toLocalDate());
                    response.setHour(weatherData.getDateTime().getHour());
                    response.setTemperature(weatherData.getTemperature());
                    return response;
                });
    }
}
