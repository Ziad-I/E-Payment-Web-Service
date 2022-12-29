package com.example.demo.Refund;

import com.example.demo.Client.Client;
import com.example.demo.Services.Service;

import java.util.Vector;

public class Refund {

    public static Vector<Refund> refunds = new Vector<>();
    private Service service;
    private Client client;
    private boolean accepted;

    public Refund(Service service, Client client)
    {
        this.service = service;
        this.client = client;
        refunds.add(this);
    }

    public void returnMoney()
    {
        client.getWallet().addBalance(service.getCost());
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
