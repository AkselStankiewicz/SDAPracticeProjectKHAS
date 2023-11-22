package org.example.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class CityWeatherDbTest {
    private CityWeatherDb cityWeatherDb;

    @BeforeEach
    void init(){
        System.out.println("BeforeEach");
     cityWeatherDb = new CityWeatherDb();
    }

    @AfterEach
    void afterAll() {
        System.out.println("afterAll");
      cityWeatherDb = null;
    }

    private CityDataEntity getEntity() {
        return new CityDataEntity(0L,"Warszawa", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));
    }

    @Test
    void shouldSaveToDbTest() {
        CityDataEntity city = getEntity();
        String name = city.getName();

        boolean result = cityWeatherDb.save(name, city);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotSaveToDbTest() {
        CityDataEntity city = getEntity();
        String name = null;

        boolean result = cityWeatherDb.save(name, city);
        name = city.getName();
        cityWeatherDb.save(name, city);
        boolean result2 = cityWeatherDb.save(name, city);

        Assertions.assertFalse(result);
        Assertions.assertFalse(result2);
    }
    @Test
    void shouldRemoveWithKeyArgumentTest() {
        CityDataEntity value = getEntity();
        String key = value.getName();

        cityWeatherDb.save(key,value);

        boolean result = cityWeatherDb.remove(key);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotRemoveWithKeyArgumentTest() {
        CityDataEntity value = getEntity();
        String key = value.getName();

        cityWeatherDb.save(key,value);

        boolean result = cityWeatherDb.remove(key + "1");

        Assertions.assertFalse(result);
    }

    @Test
    void shouldRemoveWithValueArgumentTest() {
        CityDataEntity value = getEntity();
        String key = value.getName();

        cityWeatherDb.save(key,value);
        boolean result = cityWeatherDb.remove(value);

        Assertions.assertTrue(result);
    }
    @Test
    void shouldNotRemoveWithValueArgumentTest() {
        CityDataEntity value1 = getEntity();
        CityDataEntity value3 = null;
        String key = value1.getName();
        CityDataEntity value2 = new CityDataEntity(0L,"Swiebodzin", new WeatherDataEntity(0L, 0L, LocalDate.now(), 25L, 10L,1024L));

        cityWeatherDb.save(key,value1);

        boolean result1 = cityWeatherDb.remove(value2);
        boolean result2 = cityWeatherDb.remove(value3);

        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
    }

    @Test
    void shouldModifyEntryTest() {

    }
}