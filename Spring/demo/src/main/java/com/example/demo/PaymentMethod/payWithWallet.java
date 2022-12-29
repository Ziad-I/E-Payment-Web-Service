package com.example.demo.PaymentMethod;

import com.example.demo.Client.Client;

public class payWithWallet implements IPaymentMethod {

    @Override
    public String pay(Client client, double cost) {

        if(client.getWallet().getBalance() >= cost) {
            String ret = cost + " deducted from your wallet\n";
            ret += client.getWallet().withdrawBalance(cost);
            //
            return ret;
        }
        else
            // ;
            return "insufficient money in your wallet";
    }
}
