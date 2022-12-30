package com.example.demo.Refund;

import com.example.demo.Client.Client;
import com.example.demo.Services.Service;

import java.util.Vector;

public class Refund {

    public static Vector<Refund> refunds = new Vector<>();
    private Service service;
    private boolean accepted;

    public Refund(Service service)
    {
        this.service = service;
        refunds.add(this);
    }

    public void returnMoney()
    {
        service.getClient().getWallet().addBalance(service.getCost());
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
