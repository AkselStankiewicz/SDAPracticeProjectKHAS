package org.example.db;

import java.util.HashMap;
import java.util.Map;

public class CityWeatherDb {

    private static final Map<String, CityDataEntity> dataBase = new HashMap<>();


    boolean save(String key, CityDataEntity city) {
        if (dataBase.containsKey(key))
            return false;
        dataBase.put(key, city);
        return true;
    }

    boolean remove(String key, CityDataEntity city) {
        if (dataBase.containsKey(key) || dataBase.containsValue(city)) {
            dataBase.remove(key, city);
            return true;
        }
        return false;
    }

    boolean remove(String key) {
        if (dataBase.containsValue(key)) {
            dataBase.remove(key);
            return true;
        }
        return false;
    }

    boolean remove(CityDataEntity city) {
        String key = dataBase.entrySet().
                stream().
                filter(entry -> city.equals(entry.getValue())).
                map(Map.Entry::getKey).toString();
        if (key.isEmpty() || key.equals(null))
            return false;
        dataBase.remove(key);
        return true;

    }

}
