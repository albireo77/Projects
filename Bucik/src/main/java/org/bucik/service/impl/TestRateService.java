package org.bucik.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bucik.model.RateRequest;
import org.bucik.service.RateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestRateService implements RateService {

    private static final Map<RateRequest, Double> rates = new HashMap<>();
    private static final Logger LOG = LogManager.getLogger(TestRateService.class);

    static {
        rates.put(new RateRequest("2000-01-01", "USD", "PLN"), 5.6);
        rates.put(new RateRequest("2007-03-14", "USD", "PLN"), 2.18);
        rates.put(new RateRequest("2018-11-12", "USD", "PLN"), 2.05);
    }

    @Override
    public BigDecimal getRate(RateRequest rateRequest) {
        Double rate = rates.get(rateRequest);
        if (rate != null) {
            return BigDecimal.valueOf(rate);
        } else {
            LOG.warn("Rate not found for request - {}", rateRequest);
            return BigDecimal.ZERO;
        }
    }
}
