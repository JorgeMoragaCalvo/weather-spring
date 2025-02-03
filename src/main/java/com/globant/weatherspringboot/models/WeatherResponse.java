package com.globant.weatherspringboot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    private String city;
    private double temperature;
    private String condition;
    private int humidity;
}
