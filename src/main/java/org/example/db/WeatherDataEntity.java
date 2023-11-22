package org.example.db;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class WeatherDataEntity {
	private Long id;
	private Long cityId;
	
	private LocalDateTime dateTime;
	private Float temperature;
	private Float windSpeed;
	private Float pressure;
}
