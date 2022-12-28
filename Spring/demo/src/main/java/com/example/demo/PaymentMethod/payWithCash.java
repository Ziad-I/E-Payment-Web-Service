package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public class payWithCash implements IPaymentMethod {

    @Override
    public boolean pay(Client client, double cost) {
        return true;
    }
}
