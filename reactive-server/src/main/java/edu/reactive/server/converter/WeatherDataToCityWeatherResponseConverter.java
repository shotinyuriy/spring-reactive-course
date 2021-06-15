package edu.reactive.server.converter;

import edu.reactive.server.repository.WeatherData;
import edu.reactive.server.rest.dto.CityWeatherResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataToCityWeatherResponseConverter implements Converter<WeatherData, CityWeatherResponse> {
    @Override
    public CityWeatherResponse convert(WeatherData weatherData) {
        return CityWeatherResponse.builder()
                .city(weatherData.getCity())
                .dateTime(weatherData.getDateTime())
                .temperature(weatherData.getTemperature())
                .build();
    }

    @Override
    public <U> Converter<WeatherData, U> andThen(Converter<? super CityWeatherResponse, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
