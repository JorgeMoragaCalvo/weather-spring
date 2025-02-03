package com.globant.weatherspringboot.service;

import com.globant.weatherspringboot.models.WeatherResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class WeatherService {

    private final Dotenv dotenv = Dotenv.load();
    private final String apiKey = dotenv.get("WEATHER_API_KEY");

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(String location){
        String apiUrl = UriComponentsBuilder.fromUriString("http://api.weatherapi.com/v1/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .toUriString();
        String response = restTemplate.getForObject(apiUrl, String.class);

        JSONObject json = new JSONObject(response);
        return new WeatherResponse(
                json.getJSONObject("location").getString("name"),
                json.getJSONObject("current").getDouble("temp_c"),
                json.getJSONObject("current").getJSONObject("condition").getString("text"),
                json.getJSONObject("current").getInt("humidity")
        );
    }
}
