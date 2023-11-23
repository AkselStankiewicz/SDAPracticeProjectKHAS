package org.example.db;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDataEntity {
	private Long id;
	private String name;
	private WeatherDataEntity weatherDataEntity;

	@Override
	public String toString() {
		return "\nCity ID: " + id +
				"\nCity Name: " + name +
				"\nPogoda: " + weatherDataEntity;
	}
}
