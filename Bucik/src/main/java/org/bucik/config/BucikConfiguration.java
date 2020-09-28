package org.bucik.config;

import org.bucik.service.RateService;
import org.bucik.service.impl.ExchangeRateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BucikConfiguration {

    @Bean
    RateService rateService() {
        return new ExchangeRateService(restTemplate());
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
