package org.example.services;

import org.example.api.open_weather.CityOwResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataToFile {


    boolean didFileExists(Path path) {
        return Files.exists(path);
    }

    boolean createFile(Path path) {

        boolean fileExists = didFileExists(path);
        if (fileExists) {
            return false;
        }
        else{
        try {
            System.out.println("dupa");
            Files.createFile(path);
            Files.write(path, "City name Temperature , Pressure  Wind  Date \n".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        return true;
    }

    public void saveToFile(CityOwResponse response,String inputPath) {
        Path path = Paths.get(inputPath+".csv");
        StringBuilder sb = new StringBuilder();
        boolean isCreated = createFile(path);
        if (isCreated)
            System.out.println("file was created");

        try {
            Files.write(path, sb.append(response.getName()).append(response.getMain()).append(response.getWind()).append(response.getDt()).append("\n").toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
