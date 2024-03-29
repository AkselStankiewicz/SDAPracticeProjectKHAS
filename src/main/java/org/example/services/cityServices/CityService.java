package org.example.services.cityServices;

import org.example.api.obj.City;
import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CityService {

    private Set<City> cities;

    public CityService() {
        this.cities = new HashSet<>();
    }

    public boolean isInBase(String name) {
        List<String> list = cities.stream()
                .map(City::getName)
                .filter(n -> n.equals(name))
                .toList();
       return !list.isEmpty();
    }

    public City getCity(String name) {
        return cities.stream()
                .filter(city -> city.getName().equals(name))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("No city in base.");
                    return new City(-1L, "");
                });
    }

    public boolean add(City city) {
        if (city == null) {
            System.out.println("City is null.");
            return false;
        }
        return cities.add(city);
    }

    public void showAllCities() {
        if (cities.isEmpty()) {
            System.out.println("No cities in base.");
            return;
        }
        for (City c : cities) {
            System.out.println(c);
        }
    }
    public void showAllCitiesWithDetails(CityWeatherDb cityWeatherDb) {
        Map<String, CityDataEntity> allCities = cityWeatherDb.getAll();
        if (allCities.isEmpty()) {
            System.out.println("No cities in base.");
            return;
        }
        allCities.forEach((k, v) -> {
            System.out.println("Key:\n" + k);
            System.out.println("Value: " + v + "\n");
        });
    }
}
