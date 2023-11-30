package org.example;

import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;
import org.example.services.*;
import org.example.services.cityServices.CityService;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu.showWelcomeMenu();
        CityService cityService = new CityService();
        CityWeatherDb cityWeatherDb = new CityWeatherDb();
        String userInput = "";
        while (true) {

            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();

            switch (userInput) {
                case "X" -> {
                    return;
                }
                case "Y" -> new GetAndAddToDbService().handle(cityService, cityWeatherDb);
                case "A" -> cityService.showAllCities();
                case "AD" -> {
                    Map<String, CityDataEntity> allCities = cityWeatherDb.getAll();
                    if (allCities.isEmpty()) {
                        System.out.println("No cities in base.");
                        break;
                    }
                    allCities.forEach((k, v) -> {
                        System.out.println("Key:\n" + k);
                        System.out.println("Value: " + v + "\n");
                    });
                }
                default -> System.out.println("ERROR!!! INVALID INPUT");
            }
        }
    }
}