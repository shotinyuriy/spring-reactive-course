package edu.reactive.server.service;

import edu.reactive.server.converter.WeatherDataToCityWeatherResponseConverter;
import edu.reactive.server.repository.WeatherData;
import edu.reactive.server.repository.WeatherDataRepository;
import edu.reactive.server.rest.dto.CityResponse;
import edu.reactive.server.rest.dto.CityWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class WeatherService {

    public static final int HOURS_STEP = 6;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    private WeatherDataToCityWeatherResponseConverter converter;

    private Random random = new Random(System.nanoTime());

    public Flux<CityResponse> cities() {
        return weatherDataRepository
                .findUniqueCities()
                .map(city -> new CityResponse(city));
    }

    public Mono<Boolean> createCityAndGenerateWeatherData(String city, int weatherDataCount) {
        AtomicReference<LocalDateTime> dateTime = new AtomicReference<>(LocalDate.now().plusDays(1).atTime(0, 0));
        return weatherDataRepository.count()
                .flatMapMany(repositoryCount -> Flux
                        .range(1, weatherDataCount)
                        .map(item -> repositoryCount + item)
                ).map(weatherDataId -> {
                    WeatherData weatherData = new WeatherData();
                    weatherData.setId(weatherDataId);
                    weatherData.setCity(city);
                    weatherData.setDateTime(dateTime.get());
                    weatherData.setTemperature(random.nextInt(40));
                    dateTime.set(dateTime.get().plusHours(HOURS_STEP));
                    return weatherData;
                })
                .flatMap(weatherData -> r2dbcEntityTemplate.insert(weatherData))
                .then(Mono.just(true));
    }

    public Flux<CityWeatherResponse> cityWeather(String city) {
        return weatherDataRepository.findAll()
                .filter(weatherData -> weatherData.getCity().equals(city))
                .limitRate(4)
                .map(converter::convert);
    }

    public Flux<CityWeatherResponse> cityWeatherBetweenDates(String city, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atTime(0, 0);
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59, 999999);

        return weatherDataRepository.findByCityAndDateTimeBetween(city, startDateTime, endDateTime)
                .map(converter::convert);

    }

    public Mono<WeatherData> createWeatherData(Long id, String city, LocalDateTime dateTime, int temperature) {
        WeatherData weatherData = new WeatherData();
        weatherData.setId(id);
        weatherData.setCity(city);
        weatherData.setDateTime(dateTime);
        weatherData.setTemperature(temperature);
        return weatherDataRepository.save(weatherData);
    }
}
