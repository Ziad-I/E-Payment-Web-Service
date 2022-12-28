package com.example.demo.Services;

public class ServiceFactory {

    public Service createService(String serviceName, String additional)
    {
        Service service = null;
        if(serviceName.equals("mobile recharge"))
            service = new MobileRecharge(additional);
        else if(serviceName.equals("internet payment"))
            service = new internetPayment(additional);
        else if(serviceName.equals("landline"))
            service = new landline(additional);
        else if(serviceName.equals("donations"))
            service = new Donation(additional);
        return service;
    }

}
