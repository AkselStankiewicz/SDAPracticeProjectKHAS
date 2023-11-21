package org.example.db;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
@EqualsAndHashCode
@Getter
public class WeatherDataEntity {
	private Long id;
	private Long cityId;
	
	private LocalDate date;
	private Long temperature;
	private Long windSpeed;
	private Long pressure;
}
