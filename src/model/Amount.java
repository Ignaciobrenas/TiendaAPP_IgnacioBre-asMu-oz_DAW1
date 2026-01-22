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

    private static double value;
    private static String currency;

    public Amount(double value) {
        this.value = value;
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

    public String showAmount() {
        return value + currency;
    }

    @Override
    public String toString() {
        return value + " " + currency;
    }
}
