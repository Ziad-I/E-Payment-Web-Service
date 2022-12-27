package Main.Services;

import Main.Client.Client;
import Main.Client.Transaction;
import Main.PaymentMethod.IPaymentMethod;
import Main.PaymentMethod.payWithCash;
import Main.PaymentMethod.payWithWallet;

import java.util.*;

public abstract class Service {

    private static int idCounter = 1;
    private final int serviceID;
    private double cost;
    protected Client client;
    private static Vector<String> services = new Vector<>(){{
        add("mobile recharge");
        add("internet payment");
        add("landline");
        add("donations");
    }};
    public static boolean overallDiscount = false;

    public static Map<String, Boolean> specificDiscount = new HashMap<>(){{
        put("mobile recharge", false);
        put("internet payment", false);
        put("landline", false);
        put("donations", false);
    }};
    private IPaymentMethod paymentMethod;

    private boolean cashAvailable = true;

    public Service() {
        serviceID = idCounter++;
    }

    public static boolean search(String serviceName)
    {
        for(String s:services)
        {
            if(s.equals(serviceName))
                return true;
        }
        return false;
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public static void addDiscount(String discountType, String serviceName)
    {
        if(discountType.equals("overall discount"))
            overallDiscount = true;
        else if(discountType.equals("specific discount"))
            specificDiscount.put(serviceName, true);
    }

    public static Vector<String> checkDiscounts()
    {
        Vector<String> availableDiscounts = new Vector<>();
        if(overallDiscount)
            availableDiscounts.add("overall discount");
        for(Map.Entry<String, Boolean> entry:specificDiscount.entrySet()){
            if(entry.getValue())
                availableDiscounts.add(entry.getKey());
        }
        return availableDiscounts;
    }

    public abstract double calculateCost();

    public void pay() {
        System.out.println("how would you like to pay?\n1- wallet\n2- cash");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice == 1)
            setPaymentMethod(new payWithWallet());
        else if (choice == 2) {
            if (cashAvailable)
                setPaymentMethod(new payWithCash());
            else {
                // tell the user that they can't pay with cash
                setPaymentMethod(new payWithWallet());
            }
    }
        Transaction transaction = new Transaction(client.getUsername(), cost);
        Transaction.paymentTransactions.add(transaction);
        paymentMethod.pay(client, cost);
    }

    public int getServiceID() {
        return serviceID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isCashAvailable() {
        return cashAvailable;
    }

    public void setCashAvailable(boolean cashAvailable) {
        this.cashAvailable = cashAvailable;
    }
}
