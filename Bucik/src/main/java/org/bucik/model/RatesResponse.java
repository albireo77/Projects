package org.bucik.model;

import java.util.HashMap;
import java.util.Map;

public class RatesResponse {

    private Map<String, Double> rates = new HashMap<>();
    private String base;
    private String date;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
