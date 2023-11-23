package org.example.services;

import org.example.api.WeatherApiParser;
import org.example.api.open_weather.CityOwResponse;
import org.example.api.open_weather.Main;
import org.example.api.open_weather.Wind;
import org.example.api.weatherStack.CityWsResponse;

public class MedianFromInput {

    CityOwResponse calculateAverageWeather(CityOwResponse owResponse,CityOwResponse owResponseToCompare) {

        Float windSpeed = owResponse.getWind().getSpeed();
        Float pressure = owResponse.getMain().getPressure();
        Float temp = owResponse.getMain().getTemp();
        String name = owResponse.getName();

        Float windSpeed2 = owResponseToCompare.getWind().getSpeed();
        Float pressure2 = owResponseToCompare.getMain().getPressure();
        Float temp2 = owResponseToCompare.getMain().getTemp();

        Wind wind=new Wind();
        Main main=new Main();
        wind.setSpeed((windSpeed+windSpeed2)/2.0f);
        main.setTemp((temp+temp2)/2.0f);
        main.setPressure((pressure+pressure2)/2.0f);

        return new CityOwResponse(name,wind,main,owResponse.getDt());
    }
}



