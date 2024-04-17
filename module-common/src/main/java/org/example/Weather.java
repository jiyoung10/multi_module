package org.example;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "result_msg")
    private String resultMsg;

    @Column(name = "page_no")
    private String pageNo;

    @Column(name = "num_of_rows")
    private String numOfRows;

    @Column(name = "total_count")
    private String totalCount;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "base_date", columnDefinition = "TIMESTAMP")
    private String base_date;

    @Column(name = "base_time", columnDefinition = "TIMESTAMP")
    private String base_time;

    @Column(name = "nx")
    private String nx;

    @Column(name = "ny")
    private String ny;

    @Column(name = "category")
    private String category;

    @Column(name = "obsrValue")
    private String obsrValue;

    public Weather() {

    }
    public Weather(String resultCode, String resultMsg, String pageNo, String numOfRows,
                    String totalCount, String dataType,
                   String base_date, String base_time,
                   String nx, String ny, String category, String obsrValue){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.totalCount = totalCount;
        this.dataType = dataType;
        this.base_date = base_date;
        this.base_time = base_time;
        this.nx= nx;
        this.ny = ny;
        this.category = category;
        this.obsrValue = obsrValue;
    }
    public static Weather toEntity(WeatherResponse weatherResponse){
        return new Weather(weatherResponse.getResultCode(),
                weatherResponse.getResultMsg(),
                weatherResponse.getPageNo(),
                weatherResponse.getNumOfRows(),
                weatherResponse.getTotalCount(),
                weatherResponse.getDataType(),
                weatherResponse.getBase_date(),
                weatherResponse.getBase_time(),
                weatherResponse.getNx(),
                weatherResponse.getNy(),
                weatherResponse.getCategory(),
                weatherResponse.getObsrValue());
    }

}
