package org.example.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class CityWeatherDbTest {
    CityWeatherDb cityWeatherDb;
    @BeforeEach
    void init(){
     cityWeatherDb = new CityWeatherDb();
    }

    @Test
    void shouldSaveToDb() {
        CityDataEntity city = new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
        String name = city.getName();

        boolean result = cityWeatherDb.save(name, city);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotSaveToDb() {
        CityDataEntity city = new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
        String name = null;

        boolean result = cityWeatherDb.save(name, city);
        name = city.getName();
        cityWeatherDb.save(name, city);
        boolean result2 = cityWeatherDb.save(name, city);

        Assertions.assertFalse(result);
        Assertions.assertFalse(result2);
    }

    @Test
    void shouldRemoveWithTwoArguments() {
        String key = "Swiebodzin";
        CityDataEntity city = new CityDataEntity(0L,"Świebodzin", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));

        cityWeatherDb.save(key,city);
        boolean result = cityWeatherDb.remove(key, city);

        Assertions.assertTrue(result);

    }
}