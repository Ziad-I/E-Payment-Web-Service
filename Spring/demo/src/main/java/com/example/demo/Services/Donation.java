package com.example.demo.Services;

import com.example.demo.Providers.CancerHospitals;
import com.example.demo.Providers.DonationProvider;
import com.example.demo.Providers.NGOs;
import com.example.demo.Providers.Schools;

import java.util.Scanner;

public class Donation extends Service {

    public Donation(String additional, double amount)
    {
        DonationProvider donationProvider = null;
        if(additional.equals("schools"))
            donationProvider = new Schools();
        else if(additional.equals("cancer hospitals"))
            donationProvider = new CancerHospitals();
        else if(additional.equals("NGOs"))
            donationProvider = new NGOs();

        this.setCost(amount);

        this.setCashAvailable(false);

        if(donationProvider != null)
            donationProvider.message();
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }


}
