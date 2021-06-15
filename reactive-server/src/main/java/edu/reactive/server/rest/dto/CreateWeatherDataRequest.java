package edu.reactive.server.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CreateWeatherDataRequest {
    @Min(1)
    Long id;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @Future
    private LocalDate date;

    @NotNull
    @Min(0)
    @Max(23)
    private Integer hour;

    @NotNull
    private Integer temperature;
}
