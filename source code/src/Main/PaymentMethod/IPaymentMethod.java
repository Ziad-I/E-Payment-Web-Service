package Main.PaymentMethod;

import Main.Client.Client;

public interface IPaymentMethod {

    void pay(Client client, double cost);

}
