package com.example.demo.Client;

public class Wallet {

    private double balance;

    public void addBalance(double balance)
    {
        this.balance += balance;
        System.out.println("your new balance is " + balance);
    }

    public void withdrawBalance(double balance)
    {
        this.balance -= balance;
        System.out.println("your new balance is " + this.balance);
    }

    public double getBalance() {
        return balance;
    }

}
