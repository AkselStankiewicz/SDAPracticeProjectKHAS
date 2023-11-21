package org.example.db;

import java.util.HashMap;
import java.util.Map;

public class CityWeatherDb {
	
	private static final Map<String, CityDataEntity> dataBase = new HashMap<>();

	public CityDataEntity removeFromDb(String cityName) {
		return dataBase.remove(cityName);
	}
	
	// TODO: metody do pracy nad bazą danych, dodawanie itp...
	
}
