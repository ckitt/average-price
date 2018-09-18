package com.averageprice.consumer.service;

public interface EventHandler {
    void processMessage(String content) throws Exception;
}
