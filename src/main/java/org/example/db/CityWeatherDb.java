package org.example.db;

import java.util.HashMap;
import java.util.Map;

public class CityWeatherDb {
	
	private static final Map<String, CityDataEntity> dataBase = new HashMap<>();

	public CityDataEntity modifyEntry(String cityName, CityDataEntity entity) {

		if (dataBase.containsKey(cityName)) {
			return dataBase.put(cityName, entity);
		}else {
			System.out.println("Brak klucza w bazie.");
			return entity;
		}
	}
	// TODO: metody do pracy nad bazą danych, dodawanie itp...
	
}
