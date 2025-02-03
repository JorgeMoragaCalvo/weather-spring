package com.globant.weatherspringboot.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherService {

    private final Dotenv dotenv = Dotenv.load();
    private final String apiKey = dotenv.get("WEATHER_API_KEY");
    //public String apiKey = "7b214261317e41c3b81163324252301";

    private final RestTemplate restTemplate = new RestTemplate();

    public void getWeather(String location){
        String apiUrl = UriComponentsBuilder.fromUriString("http://api.weatherapi.com/v1/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .toUriString();

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);
            if(response != null){
                @SuppressWarnings("unchecked")
                Map<String, Object> locationData = (Map<String, Object>) response.get("location");
                @SuppressWarnings("unchecked")
                Map<String, Object> currentData = (Map<String, Object>) response.get("curret");
                @SuppressWarnings("unchecked")
                Map<String, Object> conditionData = (Map<String, Object>) currentData.get("condition");

                System.out.println("Weather in " + locationData.get("name") + ";");
                System.out.println("Temperature: " + currentData.get("temp_c") + "C");
                System.out.println("Condition: " + conditionData.get("text"));
                System.out.println("Humidity: " + currentData.get("humidity") + "%");
            }
        } catch (Exception e){
            System.err.println("Error in weather: "  + e.getMessage());
        }
    }
}
