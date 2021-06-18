package edu.reactive.client.service;

import edu.reactive.client.service.dto.CityResponse;
import edu.reactive.client.service.dto.CityWeatherResponse;
import edu.reactive.client.service.dto.CreateCityRequest;
import edu.reactive.client.service.dto.CreateCityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WeatherServiceClient {

    private final WebClient webClient;

    public WeatherServiceClient(@Value("${weather-service.base-url}") String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Flux<CityWeatherResponse> cityWeather(String city) {
        return webClient.get().uri("/weather/{city}", city)
                .retrieve()
                .bodyToFlux(CityWeatherResponse.class)
                .limitRate(4);
    }

    public Flux<CityResponse> cities() {
        return webClient.get().uri("/weather/cities")
                .retrieve()
                .bodyToFlux(CityResponse.class);
    }

    public Mono<CreateCityResponse> createCity(CreateCityRequest createCityRequest) {
        log.info("createCity() createCityRequest={}", createCityRequest);
        Map<String, Object> request = Map.of(
                "city", createCityRequest.getCity(),
                "weatherDataCount", createCityRequest.getWeatherDataCount()
        );
        return webClient.post().uri("/weather/cities")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(CreateCityResponse.class);
    }
}
