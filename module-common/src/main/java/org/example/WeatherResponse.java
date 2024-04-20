package org.example;

import lombok.Data;

@Data
public class WeatherResponse {

    private String resultCode;

    private String resultMsg;

    private String pageNo;

    private String numOfRows;

    private String totalCount;

    private String dataType;

    private String base_date;

    private String base_time;

    private String fcst_time;

    private String fcst_value;

    private String nx;

    private String ny;

    private String category;

    private String obsrValue;
}
