package org.example.services.idServices;

public class WeatherIdService {
    private static Long id = 0L;

    public static Long getNewId() {
        return id++;
    }
}
