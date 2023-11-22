package org.example.api.obj;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class City {
    private Long id;
    private String name;

    public String toString() {
        return "City ID: " + id + "\nCity Name: " + name;
    }
}
