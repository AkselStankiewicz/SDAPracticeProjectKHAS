package org.example.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CityWeatherDbTest {
    private CityWeatherDb cityWeatherDb;

    @BeforeEach
    void init() {
        System.out.println("BeforeEach");
        cityWeatherDb = new CityWeatherDb();
    }

    @AfterEach
    void afterAll() {
        System.out.println("afterAll");
        cityWeatherDb = null;
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

        cityWeatherDb.save(key, value);

        boolean result = cityWeatherDb.remove(key);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotRemoveWithKeyArgumentTest() {
        CityDataEntity value = getEntity();
        String key = value.getName();

        cityWeatherDb.save(key, value);

        boolean result = cityWeatherDb.remove(key + "1");

        Assertions.assertFalse(result);
    }

    @Test
    void shouldRemoveWithValueArgumentTest() {
        CityDataEntity value = getEntity();
        String key = value.getName();

        cityWeatherDb.save(key, value);
        boolean result = cityWeatherDb.remove(value);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotRemoveWithValueArgumentTest() {
        CityDataEntity value1 = getEntity();
        CityDataEntity value3 = null;
        String key = value1.getName();
        CityDataEntity value2 = new CityDataEntity(0L, "Swiebodzin", new WeatherDataEntity(0L, 0L, LocalDateTime.now(), 25F, 10F, 1024F));

        cityWeatherDb.save(key, value1);

        boolean result1 = cityWeatherDb.remove(value2);
        boolean result2 = cityWeatherDb.remove(value3);

        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
    }

    @Test
    void shouldModifyEntryTest() {
        CityDataEntity toModify = getEntity();
        String cityName = toModify.getName();
        CityDataEntity modified = new CityDataEntity(0L, "Swiebodzin", new WeatherDataEntity(0L, 0L, LocalDateTime.now(), 25F, 10F, 1024F));

        cityWeatherDb.save(cityName, toModify);
        CityDataEntity oldEntity = cityWeatherDb.modifyEntry(cityName, modified);
        CityDataEntity valueAfterModification = cityWeatherDb.getById(0);

        Assertions.assertFalse(valueAfterModification.equals(toModify));
        Assertions.assertFalse(oldEntity.equals(toModify));
    }

    @Test
    void shouldNotModifyEntryTest() {
        CityDataEntity value = getEntity();
        String cityName = value.getName();
        CityDataEntity nullCity = null;
        CityDataEntity emptyCity = new CityDataEntity();
        String wrongKey = "";
        String nullKey = null;

        cityWeatherDb.save(cityName, value);
        CityDataEntity result1 = cityWeatherDb.modifyEntry(cityName, nullCity);
        CityDataEntity result2 = cityWeatherDb.modifyEntry(wrongKey, value);
        CityDataEntity result3 = cityWeatherDb.modifyEntry(nullKey, value);

        Assertions.assertEquals(result1, emptyCity);
        Assertions.assertEquals(result2, emptyCity);
        Assertions.assertEquals(result3, emptyCity);

    }

    private CityDataEntity getEntity() {
        return new CityDataEntity(0L, "Warszawa", new WeatherDataEntity(0L, 0L, LocalDateTime.now(), 25F, 10F, 1024F));
    }
}