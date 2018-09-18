package com.averageprice.consumer.repository;

public interface PriceRepository {
    void updatePrice(long timestamp, int price);
    int queryAveragePriceForPreviousXPrice(int recentXPrice);
}
