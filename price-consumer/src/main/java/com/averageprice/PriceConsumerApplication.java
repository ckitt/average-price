package com.averageprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PriceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceConsumerApplication.class, args);
	}
}
