package org.bucik.model;

import java.util.Objects;

public class RateRequest {

    private String date;
    private String baseCurrency;
    private String targetCurrency;

    public RateRequest() {
        super();
    }

    public RateRequest(String date, String baseCurrency, String targetCurrency) {
        this.date = date;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateRequest that = (RateRequest) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(baseCurrency, that.baseCurrency) &&
                Objects.equals(targetCurrency, that.targetCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, baseCurrency, targetCurrency);
    }

    @Override
    public String toString() {
        return String.format("Date: %s, Base Currency: %s, Target Currency: %s",
                date, baseCurrency, targetCurrency);
    }
}
