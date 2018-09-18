package com.averageprice.consumer.repository.impl;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractInfluxdbRepository {

    @Value("${spring.influxdb.url}")
    private String url;

    @Value("${spring.influxdb.username}")
    private String username;

    @Value("${spring.influxdb.password}")
    private String password;

    protected InfluxDB getInfluxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect(url, username, password);
        configDatabase(influxDB);
        configRetentionPolicy(influxDB);
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }

    public abstract void configDatabase(InfluxDB influxDB);
    public abstract void configRetentionPolicy(InfluxDB influxDB);
}
