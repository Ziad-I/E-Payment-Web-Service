package com.example.demo.Services;

import com.example.demo.Providers.DonationProvider;
import com.example.demo.Providers.internetPaymentProvider;

import java.util.Scanner;

public class Donation extends Service {
	
	public String distination = null;
    
    @Override
    public boolean init(String additional, double amount)
    {
    	for(String distination:DonationProvider.distinations)
    	{
    		if(distination.equals(additional))
    			this.distination = distination;
    	}
    	
    	if(this.distination == null)
    		return false;

        this.setCost(amount);
        return true;
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }


}
