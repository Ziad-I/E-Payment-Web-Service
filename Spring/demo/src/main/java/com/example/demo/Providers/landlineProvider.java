package com.example.demo.Providers;

public abstract class landlineProvider {

    protected double cost;
    public abstract void message();

    public double getCost() {
        return cost;
    }
}
