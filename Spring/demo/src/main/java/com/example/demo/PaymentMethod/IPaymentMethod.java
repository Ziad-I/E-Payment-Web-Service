package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public interface IPaymentMethod {

    String pay(Client client, double cost);

}
