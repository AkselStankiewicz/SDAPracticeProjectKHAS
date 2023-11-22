package org.example.db;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CityWeatherDb {

    private final Map<String, CityDataEntity> dataBase = new HashMap<>();


    CityDataEntity getById(int id) {
        return dataBase.entrySet().stream()
                .filter(entry -> entry.getValue().getId() == id)
                .map(Map.Entry::getValue)
                .toList().get(0);
    }

    boolean save(String key, CityDataEntity city) {
        if (dataBase.containsKey(key) || key == null)
            return false;
        dataBase.put(key, city);
        return true;
    }

    boolean remove(String key) {
        if (key == null || !dataBase.containsKey(key)) {
            return false;
        } else {
            dataBase.remove(key);
            return true;
        }
    }

    boolean remove(CityDataEntity city) {
        if (city == null) {
            return false;
        }
        String key = dataBase.entrySet().
                stream().
                filter(entry -> city.equals(entry.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse("");
        if (key.isEmpty() || !dataBase.containsKey(key))
            return false;
        dataBase.remove(key);
        return true;
    }

    public CityDataEntity modifyEntry(String cityName, CityDataEntity entity) {

        if (dataBase.containsKey(cityName)) {
            return dataBase.put(cityName, entity);
        } else {
            System.out.println("Brak klucza w bazie.");
            return entity;
        }
    }
    // TODO: metody do pracy nad bazą danych, dodawanie itp...

}
