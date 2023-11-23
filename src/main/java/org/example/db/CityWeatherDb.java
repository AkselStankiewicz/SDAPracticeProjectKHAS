package org.example.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityWeatherDb {

    private final Map<String, CityDataEntity> dataBase = new HashMap<>();

    public CityDataEntity getById(int id) {
        List<CityDataEntity> tmpList = dataBase.entrySet().stream()
                .filter(entry -> entry.getValue().getId() == id)
                .map(Map.Entry::getValue)
                .toList();
        if (tmpList.isEmpty()) {
            System.out.println("No such city with provided ID in database.");
            return new CityDataEntity();
        } else {
            return tmpList.get(0);
        }
    }

    public boolean save(String key, CityDataEntity city) {
        if (dataBase.containsKey(key) || key == null)
            return false;
        dataBase.put(key, city);
        return true;
    }

    public boolean remove(String key) {
        if (key == null || !dataBase.containsKey(key)) {
            return false;
        } else {
            dataBase.remove(key);
            return true;
        }
    }

    public boolean remove(CityDataEntity city) {
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

        if (cityName == null || entity == null) {
            System.out.println("Klucz lub misasto jest równe null.");
            return new CityDataEntity();
        }

        if (!dataBase.containsKey(cityName)) {
            System.out.println("Brak klucza w bazie.");
            return new CityDataEntity();
        } else {
            dataBase.put(cityName, entity);
            System.out.println("Updated the record.");
            return entity;
        }
    }

    public Map<String, CityDataEntity> getAll() {
        return new HashMap<>(dataBase);
    }

}
