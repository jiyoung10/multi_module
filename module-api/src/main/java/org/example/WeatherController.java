package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/test")
    public String test() {
        return weatherService.test();
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeatherForecast() {
        List<Weather> weatherData = weatherService.getWeatherForecast();
        return !weatherData.isEmpty() ? ResponseEntity.ok(weatherData) : ResponseEntity.noContent().build();
    }

    @PostMapping("/weather")
    public ResponseEntity<?> fetchWeatherForecast(@RequestBody WeatherRequest weatherRequest) {
        try {
            WeatherResponse response = weatherService.fetchAndSaveWeatherForecast(weatherRequest);
            // 처리 결과를 ResponseEntity로 반환
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch weather forecast: " + e.getMessage());
        }
    }
}
