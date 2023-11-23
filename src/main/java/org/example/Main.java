package org.example;

import org.example.api.obj.City;
import org.example.api.open_weather.CityOwResponse;
import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;
import org.example.db.WeatherDataEntity;
import org.example.services.*;

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
                case "Y" -> new GetAndAddToDbService().handle(cityService, cityWeatherDb);
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
}