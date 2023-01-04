package com.example.demo.Services;

import com.example.demo.Providers.*;

public class MobileRecharge extends Service {

	public String provider = null;
    
    @Override
    public boolean init(String additional, double amount)
    {
    	for(String provider:mobileRechargeProvider.providers)
    	{
    		if(provider.equals(additional))
    			this.provider = provider;
    	}
    	
    	if(this.provider == null)
    		return false;

        this.setCost(amount);
        return true;
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
