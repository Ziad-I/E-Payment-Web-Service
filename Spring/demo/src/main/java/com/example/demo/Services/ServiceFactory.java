package com.example.demo.Services;

public class ServiceFactory {

    public Service createService(String serviceName)
    {
        Service service = null;
        if(serviceName.equals("mobile recharge"))
            service = new MobileRecharge();
        else if(serviceName.equals("internet payment"))
            service = new internetPayment();
        else if(serviceName.equals("landline"))
            service = new landline();
        else if(serviceName.equals("donations"))
            service = new Donation();
        return service;
    }

}
