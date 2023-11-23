package org.example.services;

import org.example.api.HttpClientService;
import org.example.api.open_weather.CityOwResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherService {
	
	public CityOwResponse getWeatherFromOpenWeather(String sc) {
		String city=sc.trim();
		Pattern pattern = Pattern.compile(" ");
		Matcher matcher = pattern.matcher(city);
		city = matcher.replaceAll("%20");
		String openWeatherUrl = "https://api.openweathermap.org/data/2.5/weather?appid=716238e6166ce6e1315daf3232959cd3&q="+city+"&units=metric";
		
		var httpClientService = new HttpClientService<CityOwResponse>();

		final CityOwResponse response = httpClientService.getWeather(openWeatherUrl, CityOwResponse.class);

		return response;
	}
}
