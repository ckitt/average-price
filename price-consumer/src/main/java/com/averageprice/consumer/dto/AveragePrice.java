package com.averageprice.consumer.dto;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "PriceTimeSeries")
public class AveragePrice {
    @Column(name = "mean")
    private int averagePrice;

    public int getAveragePrice() {
        return averagePrice;
    }
}
