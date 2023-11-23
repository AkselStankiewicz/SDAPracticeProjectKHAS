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
        String userInput = "";
        while (!userInput.equals("X")) {

            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();

            switch (userInput) {
                case "X" -> isRunning = false;
                case "Y" -> {
                    System.out.println("Podaj nazwę miasta: ");
                    String city = scan.nextLine();
                    String parsed = String.valueOf(city.charAt(0)).toUpperCase() + city.substring(1);
                    final CityOwResponse weatherFromOpenWeather = new WeatherService().getWeatherFromOpenWeather(parsed);
                    System.out.println("City weather: " + weatherFromOpenWeather);
                    addOrUpdateCity(weatherFromOpenWeather.getName(), weatherFromOpenWeather, cityService, cityWeatherDb);
                }
                case "A" -> cityService.showAllCities();
                case "AD" -> {
                    cityWeatherDb.getAll().forEach((k, v) -> {
                        System.out.println("Key: " + k);
                        System.out.println("Value: " + v + "\n");
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
                        response.getDt(),
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