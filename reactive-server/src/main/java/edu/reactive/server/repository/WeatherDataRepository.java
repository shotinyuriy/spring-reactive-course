package edu.reactive.server.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends ReactiveCrudRepository<WeatherData, Long> {
}
