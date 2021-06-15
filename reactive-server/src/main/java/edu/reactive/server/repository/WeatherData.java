package edu.reactive.server.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("weather_data")
public class WeatherData {
    @Id
    @Column("weather_data_id")
    private Long id;

    @Column("city")
    private String city;

    @Column("date_time")
    private LocalDateTime dateTime;

    @Column("temperature")
    private Integer temperature;
}
