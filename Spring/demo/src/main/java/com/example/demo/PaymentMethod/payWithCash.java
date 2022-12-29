package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public class payWithCash implements IPaymentMethod {

    @Override
    public String pay(Client client, double cost) {
        return " will be accepted as cash on delivery\n";
    }
}
