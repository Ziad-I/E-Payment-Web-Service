package Main.Services;

import Main.Providers.*;

import java.util.Scanner;

public class MobileRecharge extends Service {

    public MobileRecharge(String additional)
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

        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the recharge amount: ");
        this.setCost(sc.nextDouble());

        if(mobileRechargeProvider != null)
            mobileRechargeProvider.message();
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
