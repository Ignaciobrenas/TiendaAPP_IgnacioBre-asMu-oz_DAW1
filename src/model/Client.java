/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Amount;

/**
 *
 * @author ignac
 */
public class Client extends Person{
    int memberId;
    Amount balance; 

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
    public String toString() {
        return "Client{" + "memberId=" + memberId + ", balance=" + balance + '}';
    }
    
}