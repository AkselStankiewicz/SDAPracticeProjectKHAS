package org.example.api.weatherStack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor

@Getter
public class CityWsResponse {
    Location location;
    Current current;

}
