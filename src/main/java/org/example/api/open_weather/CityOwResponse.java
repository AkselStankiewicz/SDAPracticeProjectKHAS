package org.example.api.open_weather;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityOwResponse {
	private String name;
	private Wind wind;
	private Main main;
	private LocalDateTime dt;
}