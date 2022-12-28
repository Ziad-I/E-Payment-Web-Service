package com.example.demo.Services;

import com.example.demo.Providers.*;

import java.util.Scanner;

public class internetPayment extends Service {

    public internetPayment(String additional)
    {
        internetPaymentProvider internetPaymentProvider = null;
        if(additional.equals("etisalat"))
            internetPaymentProvider = new InternetEtisalat();
        else if(additional.equals("orange"))
            internetPaymentProvider = new InternetOrange();
        else if(additional.equals("vodafone"))
            internetPaymentProvider = new InternetVodafone();
        else if(additional.equals("we"))
            internetPaymentProvider = new InternetWe();

        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the internet payment amount: ");
        this.setCost(sc.nextDouble());

        if(internetPaymentProvider != null)
            internetPaymentProvider.message();
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
