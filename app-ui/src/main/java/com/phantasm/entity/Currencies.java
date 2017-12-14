package com.phantasm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.ws.developer.Serialization;

import java.time.LocalDate;
import java.util.Map;

/**
 * Class to hold the JSON data received from the API.
 *
 * @author Raghav Sharma {raghav.sharma_1995@outlook.com}
 */
public class Currencies {

    @JsonProperty("base")
    private String base;

    @JsonProperty("date")
    private String date; // TODO This should be LocalDate !!

    @JsonProperty("rates")
    @JsonSerialize
    private Map<String,Double> rates;

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

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "["
                + "base: " + base + ", "
                + "date: " + date.toString() + ", "
                + "rates: ["  + rates.toString() + "]"
                + "]";
    }

}
