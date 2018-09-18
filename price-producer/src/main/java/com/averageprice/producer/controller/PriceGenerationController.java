package com.averageprice.producer.controller;

import com.averageprice.producer.service.PriceGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PriceGenerationController {

    @Autowired
    private PriceGenerationService priceGenerationService;

    @Scheduled(fixedRate = 1000)
    public void generatePriceUpdate() {
        priceGenerationService.generateNewPriceUpdate();
    }
}
