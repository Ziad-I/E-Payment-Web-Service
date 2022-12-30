package com.example.demo.Discounts;

import com.example.demo.Services.Service;

public class specificDiscount extends DiscountDecorator{

    public specificDiscount(Service service)
    {
        super(service);
    }

    @Override
    public double calculateCost() {
        return (service.getCost() - 0.2*service.getCost());
    }
    
    @Override
    public boolean init(String additional, double cost)
    {
    	return true;
    }
}
