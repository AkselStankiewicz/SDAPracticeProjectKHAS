package org.example.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.api.open_weather.CityOwResponse;
import org.example.api.open_weather.Main;
import org.example.api.open_weather.Wind;

import java.time.LocalDateTime;



public class WeatherApiParser {

    CityOwResponse parseToOwResponse(String name, Float windSpeed, Float temperature, Float pressure, LocalDateTime dt) {
        Wind wind = new Wind();
        Main main = new Main();
        main.setPressure(pressure);
        main.setTemp(temperature);
        wind.setSpeed(windSpeed);
        return new CityOwResponse(name, wind, main, dt);
    }

}
