package edu.reactive.client.route;

import edu.reactive.client.handler.WeatherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class WeatherRouter {
    @Bean
    public RouterFunction<ServerResponse> route(WeatherHandler weatherHandler) {
        return RouterFunctions.route(
                GET("/").and(accept(TEXT_HTML)),
                weatherHandler::index);
    }
}
