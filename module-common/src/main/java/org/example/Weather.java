package org.example;

import lombok.Data;

import javax.persistence.*;

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

    @Column(name = "base_date")
    private String baseDate;

    @Column(name = "base_time")
    private String baseTime;

    @Column(name = "fcst_time")
    private String fcstTime;

    @Column(name = "fcst_value")
    private String fcstValue;

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

}
