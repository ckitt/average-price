package com.averageprice.consumer.service;

import com.averageprice.consumer.dto.PriceUpdateEvent;

public interface PriceService {
    void updatePrice(PriceUpdateEvent priceUpdateEvent);

    int queryAveragePriceForPreviousXPrice(int recentXPrice);
}
