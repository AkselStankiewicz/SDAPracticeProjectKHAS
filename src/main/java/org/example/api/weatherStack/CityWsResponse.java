package org.example.api.weatherStack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class CityWsResponse {
    Location location;
    Current current;

}
