package org.example.services;

import org.example.services.parsers.WeatherApiParser;
import org.example.api.obj.City;
import org.example.api.open_weather.CityOwResponse;
import org.example.api.weatherStack.CityWsResponse;
import org.example.db.CityDataEntity;
import org.example.db.CityWeatherDb;
import org.example.services.cityServices.CityService;
import org.example.services.idServices.CityIdService;
import org.example.services.mappers.CityDataEntityMapper;
import org.example.services.weatherServices.WeatherService;

import java.util.Scanner;

public class GetAndAddToDbService {

    public void handle(CityService cityService, CityWeatherDb cityWeatherDb) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type city name: ");
        String city = scan.nextLine();
        String parsed = String.valueOf(city.charAt(0)).toUpperCase() + city.substring(1);

        WeatherService weatherService = new WeatherService();
        final CityOwResponse weatherFromOpenWeather = weatherService.getWeatherFromOpenWeather(parsed);
        final CityWsResponse weatherFromWeatherStack= weatherService.getWeatherFromWeatherStack(parsed);
        //System.out.println("City weather: " + weatherFromOpenWeather);
        //System.out.println("\n\nCity weather: " + weatherFromWeatherStack + "\n\n");

        if (weatherFromWeatherStack.getCurrent() != null && weatherFromOpenWeather.getName() != null) {
            final CityOwResponse parsedWeather = new WeatherApiParser().parseToOwResponse(weatherFromWeatherStack);
            final CityOwResponse averageWeatherFromTwoApis = new MedianFromInput().calculateAverageWeather(weatherFromOpenWeather, parsedWeather);
            addOrUpdateCity(averageWeatherFromTwoApis.getName(), averageWeatherFromTwoApis, cityService, cityWeatherDb);
        } else {
            System.out.println("One or both of requested objects are null");
        }
    }
    public CityDataEntity addOrUpdateCity(String name, CityOwResponse response, CityService cityService, CityWeatherDb db) {
        City city;
        if (cityService.isInBase(name)) {
            city = cityService.getCity(name);
        } else {
            city = new City(CityIdService.getNewId(), response.getName());
        }
        CityDataEntity cityToAdd = CityDataEntityMapper.map(city,response);

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
