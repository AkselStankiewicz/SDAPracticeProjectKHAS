package org.example.services.mappers;

import org.example.api.obj.City;
import org.example.api.open_weather.CityOwResponse;
import org.example.db.CityDataEntity;
import org.example.db.WeatherDataEntity;
import org.example.services.idServices.WeatherIdService;

import java.time.LocalDateTime;

public class CityDataEntityMapper {
    public static CityDataEntity map(City city, CityOwResponse response) {
        return new CityDataEntity(city.getId(),
                city.getName(),
                new WeatherDataEntity(
                        WeatherIdService.getNewId(),
                        city.getId(),
                        LocalDateTime.now(),
                        response.getMain().getTemp(),
                        response.getWind().getSpeed(),
                        response.getMain().getPressure()
                ));
    }
}
