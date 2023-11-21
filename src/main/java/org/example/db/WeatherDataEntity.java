package org.example.db;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WeatherDataEntity {
	private Long id;
	private Long cityId;
	
	private LocalDate date;
	private Long temperature;
	private Long windSpeed;
	private Long pressure;
}
