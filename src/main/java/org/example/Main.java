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
                case "AD" -> cityService.showAllCitiesWithDetails(cityWeatherDb);
                default -> System.out.println("ERROR!!! INVALID INPUT");
            }
        }
    }
}