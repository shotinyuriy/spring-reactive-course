package edu.reactive.client.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateCityRequest {
    private String city;
    private Integer weatherDataCount;
}
