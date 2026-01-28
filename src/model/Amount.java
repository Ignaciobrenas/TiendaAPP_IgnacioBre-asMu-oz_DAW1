/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ignac
 */
public class Amount {

    private double value;
    private String currency;

    public Amount(double value) {
        this.value = value;
        this.currency = "?";
    }

    public Amount(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String showAmount() {
        return value + " " + currency;
    }

    @Override
    public String toString() {
        return value + " " + currency;
    }
}