package edu.reactive.server.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface WeatherDataRepository extends ReactiveCrudRepository<WeatherData, Long> {


    Flux<WeatherData> findByCityAndDateTimeBetween(String city, LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query("SELECT DISTINCT wd.city FROM weather_data wd")
    Flux<String> findUniqueCities();
}
