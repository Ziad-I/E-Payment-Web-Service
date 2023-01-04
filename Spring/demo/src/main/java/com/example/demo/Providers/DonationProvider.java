package com.example.demo.Providers;

import java.util.Vector;

public abstract class DonationProvider {

	public static Vector<String> destinations = new Vector<>() {{
		add("schools");
		add("cancer hospitals");
		add("NGOs");
	}};
	

}
