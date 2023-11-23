package org.example.services.idServices;

public class CityIdService {
    private static Long id = 0L;

    public static Long getNewId() {
        return id++;
    }
}
