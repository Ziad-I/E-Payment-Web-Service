package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public class payWithCash implements IPaymentMethod {

    @Override
    public void pay(Client client, double cost) {
        System.out.println(cost + " will be accepted in cash");
    }
}
