package org.example.services;

import org.example.api.obj.City;

import java.util.HashSet;
import java.util.List;
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
        if(list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public City getCity(String name) {
        return cities.stream()
                .filter(city -> city.getName().equals(name))
                .findFirst()
                .get();
    }

    public boolean add(City city) {
        return cities.add(city);
    }

    public void showAllCities(){
        for (City c: cities) {
            System.out.println(c);
        }
    }
}
