package Main.Services;

import Main.Providers.landlineProvider;
import Main.Providers.monthlyReceipt;
import Main.Providers.quarterlyReceipt;

public class landline extends Service {

    public landline(String additional)
    {
        landlineProvider landlineProvider = null;
        if(additional.equals("monthly receipt"))
            landlineProvider = new monthlyReceipt();
        else if(additional.equals("quarterly receipt"))
            landlineProvider = new quarterlyReceipt();

        if(landlineProvider != null) {
            landlineProvider.message();
            this.setCost(landlineProvider.getCost());
        }
    }

    @Override
    public double calculateCost() {
        return this.getCost();
    }
}
