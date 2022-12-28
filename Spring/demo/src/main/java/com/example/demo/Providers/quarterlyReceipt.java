package com.example.demo.Providers;

public class quarterlyReceipt extends landlineProvider{

    public quarterlyReceipt()
    {
        cost = 100;
    }

    public void message()
    {
        System.out.println("quarterly receipt costs $" + cost);
    }

}
