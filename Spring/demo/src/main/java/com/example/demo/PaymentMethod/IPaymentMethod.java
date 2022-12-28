package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public interface IPaymentMethod {

    void pay(Client client, double cost);

}
