package com.example.demo.Providers;

import java.util.Vector;

public abstract class mobileRechargeProvider {

	public static Vector<String> providers = new Vector<>(){{
		add("etisalat");
		add("orange");
		add("vodafone");
		add("we");
	}};
	
    public abstract void message();

}
