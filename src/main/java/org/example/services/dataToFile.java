package org.example.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class dataToFile {
    Path path = Paths.get("Weather in cities.csv");

    boolean didFileExists(Path path) {
        return Files.exists(path);
    }

    boolean createFile(Path path) {
        boolean fileExists = didFileExists(path);
        if (fileExists)
            return false;
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    void saveToFile(Path path) {
        StringBuilder sb=new StringBuilder();




    }
}
