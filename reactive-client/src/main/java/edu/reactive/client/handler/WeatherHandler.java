package edu.reactive.client.handler;

import edu.reactive.client.service.CityWeatherResponse;
import edu.reactive.client.service.WeatherServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class WeatherHandler {

    private static final String QP_CITY = "city";

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    public Mono<ServerResponse> index(ServerRequest request) {
        String city = request.queryParam(QP_CITY).orElse("");
        Flux<CityWeatherResponse> cityWeatherResponses = weatherServiceClient.cityWeather(city);
        return ServerResponse
                .ok()
                .render("index", Map.of(
                        "city", city,
                        "cityWeather", cityWeatherResponses)
                );
    }
}
