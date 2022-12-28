package com.example.demo.Discounts;

import com.example.demo.Services.Service;

public abstract class DiscountDecorator extends Service {

    protected Service service;

    public DiscountDecorator(Service service)
    {
        this.service = service;
    }

    @Override
    public double calculateCost() {
        return service.calculateCost();
    }
}
