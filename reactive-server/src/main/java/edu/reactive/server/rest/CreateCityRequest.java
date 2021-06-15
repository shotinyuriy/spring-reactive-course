package edu.reactive.server.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class CreateCityRequest {
    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @Min(1)
    @Max(256)
    private Integer weatherDataCount;
}
