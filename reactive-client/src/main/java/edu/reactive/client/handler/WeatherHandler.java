package edu.reactive.client.handler;

import edu.reactive.client.service.CityResponse;
import edu.reactive.client.service.CityWeatherResponse;
import edu.reactive.client.service.WeatherServiceClient;
import edu.reactive.client.view.WeatherOnDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WeatherHandler {

    private static final String QP_CITY = "city";

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    public Mono<ServerResponse> index(ServerRequest request) {
        String city = request.queryParam(QP_CITY).orElse("");
        Mono<List<WeatherOnDate>> weatherByDate =
                weatherServiceClient.cityWeather(city)
                .collectList()
                .map(cityWeatherResponses -> cityWeatherResponses.stream()
                        .collect(Collectors.groupingBy(cityWeather -> cityWeather.getDateTime().toLocalDate()))
                        .entrySet().stream()
                        .map(groupedByDate -> new WeatherOnDate(groupedByDate.getKey(), groupedByDate.getValue()))
                        .collect(Collectors.toList()));

        Flux<CityResponse> cities = weatherServiceClient.cities();
        return ServerResponse
                .ok()
                .render("index", Map.of(
                        "cities", cities,
                        "city", city,
                        "weatherByDate", weatherByDate)
                );
    }
}
