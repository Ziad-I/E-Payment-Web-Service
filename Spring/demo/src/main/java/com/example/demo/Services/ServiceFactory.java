package com.example.demo.Services;

public class ServiceFactory {

    public Service createService(String serviceName, String additional, double amount)
    {
        Service service = null;
        if(serviceName.equals("mobile recharge"))
            service = new MobileRecharge(additional, amount);
        else if(serviceName.equals("internet payment"))
            service = new internetPayment(additional, amount);
        else if(serviceName.equals("landline"))
            service = new landline(additional);
        else if(serviceName.equals("donations"))
            service = new Donation(additional, amount);
        return service;
    }

}
