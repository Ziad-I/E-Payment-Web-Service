package Main.Services;

import Main.Client.Client;
import Main.PaymentMethod.IPaymentMethod;
import Main.PaymentMethod.payWithCash;
import Main.PaymentMethod.payWithWallet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

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
    public static Map<String, Boolean> discounts = new HashMap<>(){{
        put("overall discount", false);
        put("specific discount", false);
    }};
    private IPaymentMethod paymentMethod;


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

    public static void addDiscount(String discountType)
    {
        discounts.put(discountType, true);
    }

    public static void checkDiscounts()
    {
        if(!discounts.get("overall discount") && !discounts.get("specific discount"))
            System.out.println("no discounts available!");
        else
        {
            if (discounts.get("overall discount"))
                System.out.println("there is an overall discount");
            if (discounts.get("specific discount"))
                System.out.println("there is a specific discount");
        }
    }

    public abstract double calculateCost();

    public void pay()
    {
        System.out.println("how would you like to pay?\n1- wallet\n2- cash");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice == 1)
            setPaymentMethod(new payWithWallet());
        else if(choice == 2)
            setPaymentMethod(new payWithCash());
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
}
