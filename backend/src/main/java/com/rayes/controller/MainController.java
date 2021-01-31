package com.rayes.controller;


import com.rayes.service.CurrencyService;
import com.rayes.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class MainController {
    private static final Logger LOGGER = Logger.getLogger("MainController.class");
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping("/loadCurrency")
    public List<Currency> loadCurrency() {
        LOGGER.info("Request /api/loadCurrency");
        return currencyService.fetchCurrencyFromCRB();
    }

}
