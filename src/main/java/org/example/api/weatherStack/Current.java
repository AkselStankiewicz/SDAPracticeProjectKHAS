package org.example.api.weatherStack;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
 class Current {
    Float temperature;
    Float pressure;
    //String wind_dir;
    String wind_speed;
}
