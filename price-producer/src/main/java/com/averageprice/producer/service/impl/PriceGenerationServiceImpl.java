package com.averageprice.producer.service.impl;

import com.averageprice.producer.dto.PriceUpdateEvent;
import com.averageprice.producer.service.PriceGenerationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PriceGenerationServiceImpl implements PriceGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(PriceGenerationServiceImpl.class);

    private static final String TOPIC_PRICE_UPDATE = "priceUpdated";
    private static final Gson gson = new GsonBuilder().create();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void generateNewPriceUpdate() {
        long timestamp = System.currentTimeMillis();
        Random ran = new Random();
        int price = ran.nextInt(10000);

        PriceUpdateEvent msg = new PriceUpdateEvent();
        msg.setTimestamp(timestamp);
        msg.setPrice(price);

        String message = gson.toJson(msg).toString();

        logger.debug("Price update " + message);
        this.kafkaTemplate.send(TOPIC_PRICE_UPDATE, message);
    }
}
