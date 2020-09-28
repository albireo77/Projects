package org.bucik.service.impl;

import org.bucik.model.CalculationResponse;
import org.bucik.service.RateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatesCalculationServiceTest {

    private static RatesCalculationService underTest;

    @BeforeAll
    static void setup() {
        RateService rateService = new TestRateService();
        underTest = new RatesCalculationService(rateService);
    }

    @Test
    public void testSingleRecord() throws IOException {
        // given
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("Records_USD.csv");
        MultipartFile file = new MockMultipartFile("file", is);
        is.close();

        // when
        CalculationResponse response = underTest.calculate(file, "PLN", "0");

        // then
        assertEquals(2, response.getInterestCount());
        assertEquals(2, response.getCapitalCount());
        assertEquals("USD", response.getBaseCurrency());
        assertEquals("PLN", response.getTargetCurrency());
        assertEquals(206.02, response.getInterestBaseAmount().doubleValue());
        assertEquals(218.0380, response.getInterestTargetAmount().doubleValue());
        assertEquals(82.3, response.getCapitalBaseAmount().doubleValue());
        assertEquals(443.78, response.getCapitalTargetAmount().doubleValue());
    }
}