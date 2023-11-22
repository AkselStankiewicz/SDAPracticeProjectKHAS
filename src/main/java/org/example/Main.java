package org.example;

import org.example.api.obj.City;
import org.example.api.open_weather.CityOwResponse;
import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;
import org.example.db.WeatherDataEntity;
import org.example.services.CityService;
import org.example.services.CityIdService;
import org.example.services.WeatherIdService;
import org.example.services.WeatherService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        var isRunning = true;
        showWelcomeMenu();
        CityService cityService = new CityService();
        CityWeatherDb cityWeatherDb = new CityWeatherDb();
        while (isRunning) {

            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            switch (userInput) {
                case "X" -> isRunning = false;
                case "Y" -> {
                    final CityOwResponse weatherFromOpenWeather = new WeatherService().getWeatherFromOpenWeather(sc);
                    System.out.println("City name: " + weatherFromOpenWeather);
                    addOrUpdateCity(weatherFromOpenWeather.getName(), weatherFromOpenWeather, cityService, cityWeatherDb);
                }
                case "A" -> cityService.showAllCities();
                case "AD" -> {
                    cityWeatherDb.getAll().forEach((k, v) -> {
                        System.out.println("Key: " + k);
                        System.out.println("Value: " + v);
                    });
                }
                default -> System.out.println("ERROR!!! INVALID INPUT");
            }
        }
    }

    private static void showWelcomeMenu() {
        System.out.println("""
                ----------------------
                WELCOME!
                type X to quit
                type Y to get a weather stats
                type A to show all saved weathers
                type AD to show all saved weathers with details
                ----------------------
                """);
    }

    private static CityDataEntity addOrUpdateCity(String name, CityOwResponse response, CityService cityService, CityWeatherDb db) {
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
                        response.getDateTime(),
                        response.getMain().getTemp(),
                        response.getWind().getSpeed(),
                        response.getMain().getPressure()
                ));

        if (cityService.isInBase(city.getName())) {
            db.modifyEntry(name, cityToAdd);
            System.out.println("Updated the record.");
        } else {
            System.out.println("City added.");
            db.save(name, cityToAdd);
        }
        cityService.add(city);

        return cityToAdd;
    }
}