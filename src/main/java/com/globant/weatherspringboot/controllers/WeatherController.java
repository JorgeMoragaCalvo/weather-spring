package com.globant.weatherspringboot.controllers;

import com.globant.weatherspringboot.models.WeatherResponse;
import com.globant.weatherspringboot.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String location){
        WeatherResponse weatherData = weatherService.getWeather(location);
        return ResponseEntity.ok(weatherData);
    }
}
