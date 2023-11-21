package org.example.db;

import java.util.HashMap;
import java.util.Map;

public class CityWeatherDb {

    private static final Map<String, CityDataEntity> dataBase = new HashMap<>();


    boolean save(String key, CityDataEntity city) {
        if (dataBase.containsKey(key) || key == null)
            return false;
        dataBase.put(key, city);
        return true;
    }

    boolean remove(String key) {
        if (dataBase.containsKey(key)) {
            dataBase.remove(key);
            return true;
        }
        return false;
    }

    boolean remove(CityDataEntity city) {
        String key = dataBase.entrySet().
                stream().
                filter(entry -> city.equals(entry.getValue()))
                .map(Map.Entry::getKey).toString();
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
