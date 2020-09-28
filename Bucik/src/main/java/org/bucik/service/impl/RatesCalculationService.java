package org.bucik.service.impl;

import org.bucik.model.CalculationResponse;
import org.bucik.model.RateRequest;
import org.bucik.service.CalculationService;
import org.bucik.service.RateService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Service
public class RatesCalculationService implements CalculationService {

    private static final DateTimeFormatter ORIGINAL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TARGET_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final RateService rateService;

    public RatesCalculationService(RateService rateService) {
        this.rateService = rateService;
    }

    public CalculationResponse calculate(MultipartFile file, String targetCurrency, String correction) throws IOException {

        CalculationResponse result = new CalculationResponse();
        result.setTargetCurrency(targetCurrency);

        BigDecimal correctionBD = parseCorrection(correction);
        result.setCorrection(correctionBD.toString());
        boolean isCorrectionZero = correctionBD.signum() == 0;

        try (InputStreamReader isr = new InputStreamReader(file.getInputStream());
             BufferedReader br = new BufferedReader(isr))
        {
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains("RATY - ")) {

                    String[] tokens = line.split(";");
                    String baseCurrency = tokens[4];
                    result.setBaseCurrency(baseCurrency);

                    RateRequest rateRequest = new RateRequest(formatDate(tokens[0]), baseCurrency, targetCurrency);
                    BigDecimal rate = rateService.getRate(rateRequest);

                    BigDecimal baseAmount = new BigDecimal(tokens[3]);
                    BigDecimal targetAmount = baseAmount.multiply(rate);

                    BigDecimal correctedTargetAmount;
                    if (isCorrectionZero) {
                        correctedTargetAmount = targetAmount;
                    } else {
                        correctedTargetAmount = baseAmount.multiply(rate.add(correctionBD));
                    }

                    if (line.contains("RATY - ODSETKI")) {
                        result.increaseInterestBaseAmountBy(baseAmount);
                        result.increaseInterestTargetAmountBy(targetAmount);
                        result.increaseInterestCorrectedTargetAmountBy(correctedTargetAmount);
                        result.increaseInterestCount();
                    } else if (line.contains("RATY - KAPITA")) {
                        result.increaseCapitalBaseAmountBy(baseAmount);
                        result.increaseCapitalTargetAmountBy(targetAmount);
                        result.increaseCapitalCorrectedTargetAmountBy(correctedTargetAmount);
                        result.increaseCapitalCount();
                    }
                }
            }
        }
        return result;
    }

    private BigDecimal parseCorrection(String correction) {
        try {
            return new BigDecimal(correction);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    private static String formatDate(String dateString) {
        return TARGET_DATE_FORMAT.format(ORIGINAL_DATE_FORMAT.parse(dateString));
    }

}
