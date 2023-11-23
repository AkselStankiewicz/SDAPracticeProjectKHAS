package org.example;

import org.example.db.CityWeatherDb;
import org.example.services.*;
import org.example.services.cityServices.CityService;

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
                    cityWeatherDb.getAll().forEach((k, v) -> {
                        System.out.println("Key: " + k);
                        System.out.println("Value: " + v + "\n");
                    });
                }
                default -> System.out.println("ERROR!!! INVALID INPUT");
            }
        }
    }
}