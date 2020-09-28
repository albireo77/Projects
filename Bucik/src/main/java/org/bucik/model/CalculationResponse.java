package org.bucik.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationResponse {

    private int interestCount;
    private int capitalCount;
    private BigDecimal capitalBaseAmount = BigDecimal.ZERO;
    private BigDecimal interestBaseAmount = BigDecimal.ZERO;
    private BigDecimal capitalTargetAmount = BigDecimal.ZERO;
    private BigDecimal interestTargetAmount = BigDecimal.ZERO;
    private BigDecimal capitalCorrectedTargetAmount = BigDecimal.ZERO;
    private BigDecimal interestCorrectedTargetAmount = BigDecimal.ZERO;
    private String baseCurrency;
    private String targetCurrency;
    private String correction;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getCorrection() {
        return correction;
    }

    public BigDecimal getInterestBaseAmount() {
        return interestBaseAmount;
    }

    public BigDecimal getCapitalBaseAmount() {
        return capitalBaseAmount;
    }

    public BigDecimal getInterestTargetAmount() {
        return interestTargetAmount;
    }

    public BigDecimal getCapitalTargetAmount() {
        return capitalTargetAmount;
    }

    public void increaseInterestCount() {
        interestCount++;
    }

    public void increaseCapitalCount() {
        capitalCount++;
    }

    public int getInterestCount() {
        return interestCount;
    }

    public int getCapitalCount() {
        return capitalCount;
    }

    public void increaseCapitalBaseAmountBy(BigDecimal amount) {
        capitalBaseAmount = capitalBaseAmount.add(amount);
    }

    public void increaseInterestBaseAmountBy(BigDecimal amount) {
        interestBaseAmount = interestBaseAmount.add(amount);
    }

    public void increaseCapitalTargetAmountBy(BigDecimal amount) {
        capitalTargetAmount = capitalTargetAmount.add(amount);
    }

    public void increaseInterestTargetAmountBy(BigDecimal amount) {
        interestTargetAmount = interestTargetAmount.add(amount);
    }

    public void increaseCapitalCorrectedTargetAmountBy(BigDecimal amount) {
        capitalCorrectedTargetAmount = capitalCorrectedTargetAmount.add(amount);
    }

    public void increaseInterestCorrectedTargetAmountBy(BigDecimal amount) {
        interestCorrectedTargetAmount = interestCorrectedTargetAmount.add(amount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Liczba rat kapitałowych: ")
                .append(capitalCount)
                .append("<br>Liczba rat odsetkowych: ")
                .append(interestCount)
                .append("<br><br>Kwoty w ")
                .append(baseCurrency)
                .append(":<br>Odsetki: ")
                .append(interestBaseAmount)
                .append("<br>Kapitał: ")
                .append(capitalBaseAmount)
                .append("<br>Suma: ")
                .append(capitalBaseAmount.add(interestBaseAmount))
                .append("<br><br>Kwoty w ")
                .append(targetCurrency)
                .append(" według kursu historycznego ")
                .append(baseCurrency)
                .append("/")
                .append(targetCurrency)
                .append(" ECB:<br>Odsetki: ")
                .append(interestTargetAmount.setScale(2, RoundingMode.HALF_EVEN))
                .append("<br>Kapitał ")
                .append(capitalTargetAmount.setScale(2, RoundingMode.HALF_EVEN))
                .append("<br>Suma: ")
                .append(interestTargetAmount.add(capitalTargetAmount).setScale(2, RoundingMode.HALF_EVEN))
                .append("<br><br>Kwoty w ")
                .append(targetCurrency)
                .append(" z założeniem stałej różnicy kursu ")
                .append(baseCurrency)
                .append("/")
                .append(targetCurrency)
                .append(" pomiędzy ECB a bankiem w wysokości ")
                .append(correction)
                .append(":<br>Odsetki: ")
                .append(interestCorrectedTargetAmount.setScale(2, RoundingMode.HALF_EVEN))
                .append("<br>Kapitał: ")
                .append(capitalCorrectedTargetAmount.setScale(2, RoundingMode.HALF_EVEN))
                .append("<br>Suma: ")
                .append(interestCorrectedTargetAmount.add(capitalCorrectedTargetAmount).setScale(2, RoundingMode.HALF_EVEN));
        return sb.toString();
    }
}
