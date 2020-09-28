package org.bucik.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bucik.model.RateRequest;
import org.bucik.model.RatesResponse;
import org.bucik.service.RateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExchangeRateService implements RateService {

    private static final String URL = "https://api.exchangeratesapi.io/{date}?base={baseCurrency}&symbols={targetCurrency}";
    private static final Logger LOG = LogManager.getLogger(ExchangeRateService.class);

    private final RestTemplate restTemplate;
    private final Map<RateRequest, Double> rateCache = new ConcurrentHashMap<>();

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getRate(RateRequest rateRequest) {

        Double rate = rateCache.get(rateRequest);
        if (rate == null) {
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("date", rateRequest.getDate());
            uriVariables.put("baseCurrency", rateRequest.getBaseCurrency());
            uriVariables.put("targetCurrency", rateRequest.getTargetCurrency());
            RatesResponse ratesResponse = restTemplate.getForObject(URL, RatesResponse.class, uriVariables);
            rate = ratesResponse.getRates().get(rateRequest.getTargetCurrency());
            if (rate != null) {
                rateCache.put(rateRequest, rate);
            } else {
                LOG.warn("Exchange rate not found for request - {}", rateRequest);
                rate = 0.0;
            }
        }
        return BigDecimal.valueOf(rate);
    }
}
