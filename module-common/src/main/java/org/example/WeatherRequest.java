package org.example;

import lombok.Data;


@Data
public class WeatherRequest {

    private String pageNo;

    private String numOfRows;

    private String dataType;

    private String base_date;

    private String base_time;

    private String nx;

    private String ny;

}

