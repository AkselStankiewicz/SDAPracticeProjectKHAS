package org.example.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.api.open_weather.CityOwResponse;
import org.example.api.open_weather.Main;
import org.example.api.open_weather.Wind;
import org.example.api.weatherStack.CityWsResponse;

import java.time.LocalDateTime;



public class WeatherApiParser {

    public CityOwResponse parseToOwResponse(CityWsResponse wsResponse) {
        Wind wind = new Wind();
        Main main = new Main();
        main.setPressure(wsResponse.getCurrent().getPressure());
        main.setTemp(wsResponse.getCurrent().getTemperature());
        wind.setSpeed(wsResponse.getCurrent().getWind_speed());
        return new CityOwResponse(wsResponse.getLocation().getName(), wind, main, wsResponse.getLocation().getLocaltime_epoch());
    }

}
