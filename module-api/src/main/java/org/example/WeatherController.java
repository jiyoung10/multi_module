package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    public final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeatherForecast() {
        List<Weather> weatherData = weatherService.getWeatherForecast();
        return !weatherData.isEmpty() ? ResponseEntity.ok(weatherData) : ResponseEntity.noContent().build();
    }

    @PostMapping("/weather")
    public ResponseEntity<List<Weather>> fetchWeatherForecast(@RequestBody WeatherRequest weatherRequest) {
        try {
            List<Weather> response = weatherService.fetchAndSaveWeatherForecast(weatherRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return null;
        }
    }
}
