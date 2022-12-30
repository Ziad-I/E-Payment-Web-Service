package com.example.demo.Providers;

import java.util.Vector;

public abstract class DonationProvider {

	public static Vector<String> distinations = new Vector<>() {{
		add("schools");
		add("cancer hospitals");
		add("NGOs");
	}};
	
    public abstract void message();

}
