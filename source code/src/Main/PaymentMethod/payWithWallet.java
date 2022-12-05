package Main.PaymentMethod;

import Main.Client.Client;

public class payWithWallet implements IPaymentMethod {

    @Override
    public void pay(Client client, double cost) {
        if(client.getWallet().getBalance() >= cost) {
            client.getWallet().withdrawBalance(cost);
            System.out.println(cost + " deducted from your wallet");
        }
        else
            System.out.println("insufficient money");
    }
}
