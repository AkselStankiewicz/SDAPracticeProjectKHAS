package org.example.api.open_weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class CityOwResponse {
	private String name;
	private Wind wind;
	private Main main;
	private LocalDateTime dt;
}
