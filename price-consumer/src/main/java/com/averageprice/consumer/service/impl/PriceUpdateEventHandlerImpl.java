package com.averageprice.consumer.service.impl;

import com.averageprice.consumer.dto.PriceUpdateEvent;
import com.averageprice.consumer.service.EventHandler;
import com.averageprice.consumer.service.PriceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PriceUpdateEventHandlerImpl implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(PriceUpdateEventHandlerImpl.class);

    private static final String TOPIC_PRICE_UPDATE = "priceUpdated";
    private static final Gson gson = new GsonBuilder().create();

    @Autowired
    private PriceService priceService;

    @KafkaListener(topics = TOPIC_PRICE_UPDATE)
    public void processMessage(String content) throws Exception {
        logger.debug("Received message " + content);
        PriceUpdateEvent priceUpdateEvent = gson.fromJson(content, PriceUpdateEvent.class);
        if (priceUpdateEvent.getTimestamp() == null || priceUpdateEvent.getPrice() == null) {
            throw new Exception("Invalid message");
        }
        priceService.updatePrice(priceUpdateEvent);
    }
}
