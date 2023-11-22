package org.example;

import org.example.api.open_weather.CityOwResponse;
import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;
import org.example.services.DataToFile;
import org.example.services.WeatherService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        CityWeatherDb db = new CityWeatherDb();
        DataToFile data = new DataToFile();

        var isRunning = true;
        var isFirstRun = true;
        while (isRunning) {
            if (isFirstRun) {
                System.out.println("""
                        ----------------------
                        WELCOME!
                        type X to quit
                        type Y to get a weather stats
                        ----------------------
                        """);
                isFirstRun = false;
            }

            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            CityDataEntity cityData = new CityDataEntity();
            switch (userInput) {
                case "X" -> isRunning = false;
                case "Y" -> {
                    final CityOwResponse weatherFromOpenWeather = new WeatherService().getWeatherFromOpenWeather(sc);
                    data.saveToFile(weatherFromOpenWeather, "Weather in cities");

                    System.out.println("City name: " + weatherFromOpenWeather);
                }

                default -> System.out.println("ERROR!!! INVALID INPUT");
            }
        }
    }
}
