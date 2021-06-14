package edu.reactive.server.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("\"WEATHER_DATA\"")
public class WeatherData {
    @Id
    @Column("WEATHER_DATA_ID")
    private Long id;

    @Column("CITY")
    private String city;

    @Column("DATE_TIME")
    private LocalDateTime dateTime;

    @Column("TEMPERATURE")
    private Integer temperature;
}
