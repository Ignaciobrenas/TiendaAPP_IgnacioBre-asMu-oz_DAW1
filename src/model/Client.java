/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Amount;
import main.Payable;

/**
 * 
 * @author ignac
 */
public class Client extends Person implements Payable {
    private int memberId;
    private Amount balance;
    
    private static final int MEMBER_ID = 456;
    private static final double BALANCE = 50.00;

    public Client(int memberId, Amount balance, String name) {
        super(name);
        this.memberId = memberId;
        this.balance = balance;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean pay(Amount amount) {
        double finalBalance = balance.getValue() - amount.getValue();
        
        if (finalBalance > 0) {
            balance.setValue(finalBalance);
            return true;
            
        } else {
            
            return false;
        }
        
    }

    @Override
    public String toString() {
        return "Client{" + "memberId=" + memberId + ", balance=" + balance + ", name=" + name + '}';
    }
    
}