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
    void shouldRemoveWithKeyArgument() {
        CityDataEntity value = new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
        String key = value.getName();

        cityWeatherDb.save(key,value);

        boolean result = cityWeatherDb.remove(key);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotRemoveWithKeyArgument() {
        CityDataEntity value = new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
        String key = value.getName();

        cityWeatherDb.save(key,value);

        boolean result = cityWeatherDb.remove(key + "1");

        Assertions.assertFalse(result);
    }

    @Test
    void shouldRemoveWithValueArgument() {
        CityDataEntity value = new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
        String key = value.getName();

        cityWeatherDb.save(key,value);
        boolean result = cityWeatherDb.remove(value);

        Assertions.assertTrue(result);
    }
    @Test
    void shouldNotRemoveWithValueArgument() {
        CityDataEntity value1 = new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
        String key = value1.getName();
        CityDataEntity value2 = new CityDataEntity(0L,"Swiebodzin", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));

        cityWeatherDb.save(key,value1);

        boolean result = cityWeatherDb.remove(value2);

        Assertions.assertFalse(result);
    }
}