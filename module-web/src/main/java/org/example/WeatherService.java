package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Value("${weather.api.url}")
    private String url;

    @Value("${weather.api.serviceKey}")
    private String serviceKey;

    @Transactional
    public List<Weather> fetchAndSaveWeatherForecast(WeatherRequest weatherRequest) {
        // 경기도 의정부시 문충로74 지역
//        final String pageNo = "1";
//        final String numOfRows = "30";
//        final String dataType = "JSON";
//        final String base_date = "20240420"; // 오늘 날짜
//        final String base_time = "0500"; // 5시 기준
//        final String nx = "38";
//        final String ny = "127";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(
                url + "?serviceKey=" + serviceKey +
                        "&pageNo=" + weatherRequest.getPageNo() +
                        "&numOfRows=" + weatherRequest.getNumOfRows() +
                        "&dataType=" + weatherRequest.getDataType() +
                        "&base_date=" + weatherRequest.getBase_date() +
                        "&base_time=" + weatherRequest.getBase_time() +
                        "&nx=" + weatherRequest.getNx() +
                        "&ny=" + weatherRequest.getNy(), String.class);

        System.out.println("Response code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());

        // 응답을 Weather 엔티티로 매핑
        String responseBody = response.getBody();
        List<Weather> weather = mapResponseToWeather(responseBody);

        return weather;
    }

    private List<Weather> mapResponseToWeather(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            // JSON 응답을 JsonNode로 파싱
            JsonNode rootNode = objectMapper.readTree(responseBody);

            // 필요한 결과 값들을 추출하여 엔티티에 저장
            Weather weather = new Weather();
            weather.setResultCode(rootNode.path("response").path("header").path("resultCode").asText());
            weather.setResultMsg(rootNode.path("response").path("header").path("resultMsg").asText());
            weather.setDataType(rootNode.path("response").path("body").path("dataType").asText());

            // items 배열을 가져옴
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
            List<Weather> weatherList = new ArrayList<>();

            // items 배열의 각 요소에 대해 반복하여 값을 추출하여 엔티티에 저장
            for (JsonNode itemNode : itemsNode) {
                Weather weatherItem = new Weather(); // 새로운 Weather 객체 생성
                weatherItem.setResultCode(weather.getResultCode());
                weatherItem.setResultMsg(weather.getResultMsg());
                weatherItem.setDataType(weather.getDataType());

                weatherItem.setBaseDate(itemNode.path("baseDate").asText());
                weatherItem.setBaseTime(itemNode.path("baseTime").asText());
                weatherItem.setCategory(itemNode.path("category").asText());
                weatherItem.setFcstTime(itemNode.path("fcstTime").asText());
                weatherItem.setFcstValue(itemNode.path("fcstValue").asText());
                weatherItem.setNx(itemNode.path("nx").asText());
                weatherItem.setNy(itemNode.path("ny").asText());
                weatherItem.setObsrValue(itemNode.path("obsrValue").asText());

                // 데이터베이스 저장 및 반환값 저장
                weatherRepository.save(weatherItem);
                weatherList.add(weatherItem);
            }
            return weatherList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Weather> getWeatherForecast() {
        return weatherRepository.findAll();
    }
}
