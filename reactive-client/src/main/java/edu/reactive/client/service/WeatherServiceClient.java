package edu.reactive.client.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class WeatherServiceClient {

    private final WebClient webClient;



    public WeatherServiceClient(@Value("${weather-service.base-url}") String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Flux<CityWeatherResponse> cityWeather(String city) {
        return webClient.get().uri("/weather/{city}", city)
                .retrieve()
                .bodyToFlux(CityWeatherResponse.class);
    }

    public Flux<CityResponse> cities() {
        return webClient.get().uri("/weather/cities")
                .retrieve()
                .bodyToFlux(CityResponse.class);
    }
}
