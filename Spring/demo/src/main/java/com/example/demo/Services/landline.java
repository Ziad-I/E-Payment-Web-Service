package com.example.demo.Services;

import java.util.Map;

import com.example.demo.Providers.landlineProvider;
public class landline extends Service {
	
	public String receiptType = null;
	
    @Override
    public boolean init(String additional, double amount)
    {
    	double cost = 0.0;
    	for(Map.Entry<String, Double> entry:landlineProvider.receipts.entrySet())
    	{
    		if(entry.getKey().equals(additional)) {
    			this.receiptType = additional;
    			cost = entry.getValue();
    		}
    	}
    	
    	if(this.receiptType == null)
    		return false;

        this.setCost(cost);
        return true;
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
