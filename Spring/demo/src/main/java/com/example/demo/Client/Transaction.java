package com.example.demo.Client;

import java.util.Vector;

public class Transaction {

    private String clientUsername;
    private double Money;

    public Transaction(String clientUsername, double money) {
        this.clientUsername = clientUsername;
        this.Money = money;
    }

    public static Vector<Transaction> refundTransactions =  new Vector<>();
    public static Vector<Transaction> addToWalletTransactions =  new Vector<>();
    public static Vector<Transaction> paymentTransactions =  new Vector<>();
}
