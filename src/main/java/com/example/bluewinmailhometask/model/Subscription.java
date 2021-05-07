package com.example.bluewinmailhometask.model;

import lombok.Data;

@Data
public class Subscription {
    private double payment;
    private String currency;

    public Subscription(double payment, String currency) {
        this.payment = payment;
        this.currency = currency;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
