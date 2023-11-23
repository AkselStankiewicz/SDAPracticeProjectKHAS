package org.example.api.weatherStack;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Current {
    Float temperature;
    Float pressure;
    //String wind_dir;
    Float wind_speed;
}
