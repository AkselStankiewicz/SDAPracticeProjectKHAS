package org.example.services;

import org.example.api.HttpClientService;
import org.example.api.open_weather.CityOwResponse;

import java.util.Scanner;

public class WeatherService {
	
	public CityOwResponse getWeatherFromOpenWeather(Scanner sc) {
		System.out.println("podaj nazwę miasta");
		String city=sc.nextLine().trim();
		String openWeatherUrl = "https://api.openweathermap.org/data/2.5/weather?appid=716238e6166ce6e1315daf3232959cd3&q="+city+"&units=metric";
		
		var httpClientService = new HttpClientService<CityOwResponse>();
		final CityOwResponse response = httpClientService.getWeather(openWeatherUrl, CityOwResponse.class);
		
		return response;
	}
	
}
