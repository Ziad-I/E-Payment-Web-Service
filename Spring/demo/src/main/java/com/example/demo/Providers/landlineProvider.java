package com.example.demo.Providers;

import java.util.HashMap;
import java.util.Map;

public abstract class landlineProvider {
	
	public static Map<String, Double> receipts = new HashMap<>() {{
		put("quarterly receipt", 100.0);
		put("monthly receipt", 30.0);
	}};

    protected double cost;
    public abstract void message();

    public double getCost() {
        return cost;
    }
}
