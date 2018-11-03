package com.in28minutes.currencyexchangeservice.controllers;

import com.in28minutes.currencyexchangeservice.domain.ExchangeValue;
import com.in28minutes.currencyexchangeservice.repositories.ExchangeValueRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Environment environment;

    private ExchangeValueRepository exchangeValueRepository;

    public CurrencyExchangeController(Environment environment, ExchangeValueRepository exchangeValueRepository) {
        this.environment = environment;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);

        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
