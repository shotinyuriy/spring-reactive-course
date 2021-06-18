package edu.reactive.client.handler;

import edu.reactive.client.service.dto.CityResponse;
import edu.reactive.client.service.WeatherServiceClient;
import edu.reactive.client.view.WeatherOnDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class WeatherHandler {

    private static final String QP_CITY = "city";

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    public Mono<ServerResponse> index(ServerRequest request) {
        Flux<CityResponse> cities = weatherServiceClient.cities();
        if (request.queryParam(QP_CITY).isPresent()) {
            String city = request.queryParam(QP_CITY).get();
            Mono<List<WeatherOnDate>> weatherByDate =
                    weatherServiceClient.cityWeather(city)
                            .collectList()
                            .map(cityWeatherResponses -> cityWeatherResponses.stream()
                                    .collect(Collectors.groupingBy(cityWeather -> cityWeather.getDateTime().toLocalDate()))
                                    .entrySet().stream()
                                    .map(groupedByDate -> new WeatherOnDate(groupedByDate.getKey(), new TreeSet<>(groupedByDate.getValue())))
                                    .sorted(Comparator.comparing(WeatherOnDate::getLocalDate))
                                    .collect(Collectors.toList()));
            return ServerResponse
                    .ok()
                    .render("index", Map.of(
                            "cities", cities,
                            "city", city,
                            "city?", true,
                            "weatherByDate", weatherByDate)
                    );
        } else {
            return ServerResponse
                    .ok()
                    .render("index", Map.of("cities", cities));
        }


    }
}
