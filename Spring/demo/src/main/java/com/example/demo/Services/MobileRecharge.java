package com.example.demo.Services;

import com.example.demo.Providers.*;

import java.util.Scanner;

public class MobileRecharge extends Service {

    public MobileRecharge(String additional, double amount)
    {
        mobileRechargeProvider mobileRechargeProvider = null;
        if(additional.equals("etisalat"))
            mobileRechargeProvider = new MobileEtisalat();
        else if(additional.equals("orange"))
            mobileRechargeProvider = new MobileOrange();
        else if(additional.equals("vodafone"))
            mobileRechargeProvider = new MobileVodafone();
        else if(additional.equals("we"))
            mobileRechargeProvider = new MobileWe();

        this.setCost(amount);

        if(mobileRechargeProvider != null)
            mobileRechargeProvider.message();
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
