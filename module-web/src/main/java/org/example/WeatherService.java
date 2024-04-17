package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public String test() {
        return "api의 Bean Class 테스트";
    }

    private String serviceKey = "SERVICE_KEY=rGfXs9wuDm3uIXJIe1QONu39eUSPVUB4AYDNgyTdoG32%2B0%2FnJgXVKxZx683bFTmgJoQC5EVeeeFOBp0sYiR1AA%3D%3D";
    private String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    @Transactional
    public WeatherResponse fetchAndSaveWeatherForecast(WeatherRequest weatherRequest) {
        WeatherResponse response = new WeatherResponse();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 파라미터 구성
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("pageNo", weatherRequest.getPageNo())
                .queryParam("numOfRows", weatherRequest.getNumOfRows())
                .queryParam("dataType", weatherRequest.getDataType())
                .queryParam("base_date", weatherRequest.getBase_date())
                .queryParam("base_time", weatherRequest.getBase_time())
                .queryParam("nx", weatherRequest.getNx())
                .queryParam("ny", weatherRequest.getNy());

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // GET 요청 보내기
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, WeatherResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            WeatherResponse weatherResponse = responseEntity.getBody();
            // 응답 처리 로직 추가

            weatherRepository.save(Weather.toEntity(weatherResponse));

            response.setResultCode("200");
            response.setResultMsg("Success");
            return response;
        }

        response.setResultCode("204");
        response.setResultMsg("Fail");
        return response;
    }


    public List<Weather> getWeatherForecast() {
        return weatherRepository.findAll();
    }
}
