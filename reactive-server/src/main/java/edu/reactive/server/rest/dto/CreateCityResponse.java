package edu.reactive.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCityResponse {
    @JsonProperty("status")
    private Boolean status;
}
