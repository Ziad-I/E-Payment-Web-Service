package Main.Services;

import Main.Providers.CancerHospitals;
import Main.Providers.DonationProvider;
import Main.Providers.NGOs;
import Main.Providers.Schools;

import java.util.Scanner;

public class Donation extends Service {

    public Donation(String additional)
    {
        DonationProvider donationProvider = null;
        if(additional.equals("schools"))
            donationProvider = new Schools();
        else if(additional.equals("cancer hospitals"))
            donationProvider = new CancerHospitals();
        else if(additional.equals("NGOs"))
            donationProvider = new NGOs();

        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the donation amount: ");
        this.setCost(sc.nextDouble());

        this.setCashAvailable(false);

        if(donationProvider != null)
            donationProvider.message();
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }


}
