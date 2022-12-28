package com.example.demo.Services;

import com.example.demo.Providers.landlineProvider;
import com.example.demo.Providers.monthlyReceipt;
import com.example.demo.Providers.quarterlyReceipt;

public class landline extends Service {

    public landline(String additional)
    {
        landlineProvider landlineProvider = null;
        if(additional.equals("monthly receipt"))
            landlineProvider = new monthlyReceipt();
        else if(additional.equals("quarterly receipt"))
            landlineProvider = new quarterlyReceipt();

        if(landlineProvider != null) {
            landlineProvider.message();
            this.setCost(landlineProvider.getCost());
        }

        this.setCashAvailable(false);
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
