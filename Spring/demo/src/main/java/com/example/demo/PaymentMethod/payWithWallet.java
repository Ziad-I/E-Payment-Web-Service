package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public class payWithWallet implements IPaymentMethod {

    @Override
    public boolean pay(Client client, double cost) {
        if(client.getWallet().getBalance() >= cost) {
            client.getWallet().withdrawBalance(cost);
            // cost + " deducted from your wallet";
            return true;
        }
        else
            // "insufficient money in your wallet";
            return false;
    }
}
