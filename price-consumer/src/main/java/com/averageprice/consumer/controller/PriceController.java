package com.averageprice.consumer.controller;

import com.averageprice.consumer.dto.AveragePriceView;
import com.averageprice.consumer.dto.ErrorView;
import com.averageprice.consumer.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("price")
public class PriceController {

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    private PriceService priceService;

    @GetMapping("/average/{x}")
    public @ResponseBody AveragePriceView getAveragePrice(@PathVariable int x) throws Exception {
        if (x <= 0) {
            throw new Exception("Invalid value for x");
        }

        int averagePrice = priceService.queryAveragePriceForPreviousXPrice(x);

        AveragePriceView averagePriceView = new AveragePriceView();
        averagePriceView.setX(x);
        averagePriceView.setAveragePrice(averagePrice);

        return averagePriceView;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ErrorView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Error when request for average price", ex);
        ErrorView errorView = new ErrorView();
        errorView.setMessage(ex.getMessage());
        errorView.setException(ex);
        return errorView;
    }

}
