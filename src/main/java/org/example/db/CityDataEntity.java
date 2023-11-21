package org.example.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class CityDataEntity {
	private Long id;
	private String name;
	private WeatherDataEntity weatherDataEntity;
}
