package com.averageprice.consumer.repository.impl;

import com.averageprice.consumer.dto.AveragePrice;
import com.averageprice.consumer.repository.PriceRepository;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class PriceRepositoryImpl extends AbstractInfluxdbRepository implements PriceRepository {
    private static final Logger logger = LoggerFactory.getLogger(PriceRepositoryImpl.class);
    private static final String INFLUXDB_PRICE = "PriceDB";
    private static final String INFLUXDB_PRICE_MEASUREMENT = "PriceTimeSeries";
    private static final String INFLUXDB_PRICE_RP = "defaultConsumerRetention";

    @Override
    public void configDatabase(InfluxDB influxDB) {
        influxDB.createDatabase(INFLUXDB_PRICE);
        influxDB.setDatabase(INFLUXDB_PRICE);
    }

    @Override
    public void configRetentionPolicy(InfluxDB influxDB) {
        influxDB.createRetentionPolicy(INFLUXDB_PRICE_RP, INFLUXDB_PRICE, "30d", "30m", 2, true);
        influxDB.setRetentionPolicy(INFLUXDB_PRICE_RP);
    }

    @Override
    public void updatePrice(long timestamp, int price) {
        logger.debug("Update Price timestamp=" + timestamp + " price=" + price);

        InfluxDB influxDB = getInfluxDB();

        influxDB.write(Point.measurement(INFLUXDB_PRICE_MEASUREMENT)
                .time(timestamp, TimeUnit.MILLISECONDS)
                .addField("price", price)
                .build());

        influxDB.close();
    }

    @Override
    public int queryAveragePriceForPreviousXPrice(int recentXPrice) {
        logger.debug("Query for the average recent " + recentXPrice + " price");

        String queryString = String.format(
                "SELECT MEAN(price) FROM (" +
                        "SELECT price FROM PriceTimeSeries ORDER BY time DESC LIMIT %d" +
                        ") ORDER BY time DESC",
                recentXPrice
        );
        Query query = new Query(queryString, INFLUXDB_PRICE);

        InfluxDB influxDB = getInfluxDB();
        QueryResult queryResult = influxDB.query(query);
        influxDB.close();

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<AveragePrice> averagePriceModelList = resultMapper.toPOJO(queryResult, AveragePrice.class);

        return averagePriceModelList.get(0).getAveragePrice();
    }
}
