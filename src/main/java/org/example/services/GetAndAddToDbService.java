package org.example.services;

import org.example.api.obj.City;
import org.example.api.open_weather.CityOwResponse;
import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;
import org.example.db.WeatherDataEntity;

import java.time.LocalDateTime;
import java.util.Scanner;

public class GetAndAddToDbService {

    public void handle(CityService cityService, CityWeatherDb cityWeatherDb) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj nazwę miasta: ");
        String city = scan.nextLine();
        String parsed = String.valueOf(city.charAt(0)).toUpperCase() + city.substring(1);
        final CityOwResponse weatherFromOpenWeather = new WeatherService().getWeatherFromOpenWeather(parsed);
        System.out.println("City weather: " + weatherFromOpenWeather);
        addOrUpdateCity(weatherFromOpenWeather.getName(), weatherFromOpenWeather, cityService, cityWeatherDb);
    }
    public CityDataEntity addOrUpdateCity(String name, CityOwResponse response, CityService cityService, CityWeatherDb db) {
        City city;
        if (cityService.isInBase(name)) {
            city = cityService.getCity(name);
        } else {
            city = new City(CityIdService.getNewId(), response.getName());
        }
        CityDataEntity cityToAdd = new CityDataEntity(city.getId(),
                city.getName(),
                new WeatherDataEntity(
                        WeatherIdService.getNewId(),
                        city.getId(),
                        LocalDateTime.now(),
                        response.getMain().getTemp(),
                        response.getWind().getSpeed(),
                        response.getMain().getPressure()
                ));

        System.out.println("City to add weather ID: " + cityToAdd.getWeatherDataEntity().getId());

        if (cityService.isInBase(city.getName())) {
            db.modifyEntry(name, cityToAdd);
        } else {
            System.out.println("City added.");
            db.save(name, cityToAdd);
        }
        cityService.add(city);

        return cityToAdd;
    }
}
