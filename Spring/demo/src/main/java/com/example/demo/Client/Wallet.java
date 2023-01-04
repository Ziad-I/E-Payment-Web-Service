package com.example.demo.Client;

public class Wallet {

    private double balance;

    public double addBalance(double balance)
    {
        this.balance += balance;
        return  this.balance;
    }

    public String withdrawBalance(double balance)
    {
        this.balance -= balance;
        return "your new balance is: " + this.balance + "\n";
    }

    public double getBalance() {
        return balance;
    }

}
