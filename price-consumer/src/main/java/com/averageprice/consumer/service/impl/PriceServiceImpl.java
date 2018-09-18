package com.averageprice.consumer.service.impl;

import com.averageprice.consumer.dto.PriceUpdateEvent;
import com.averageprice.consumer.repository.PriceRepository;
import com.averageprice.consumer.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public void updatePrice(PriceUpdateEvent priceUpdateEvent) {
        priceRepository.updatePrice(priceUpdateEvent.getTimestamp(), priceUpdateEvent.getPrice());
    }

    public int queryAveragePriceForPreviousXPrice(int recentXPrice) {
        return priceRepository.queryAveragePriceForPreviousXPrice(recentXPrice);
    }
}
