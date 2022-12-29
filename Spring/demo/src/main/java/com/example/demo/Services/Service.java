package com.example.demo.Services;

import com.example.demo.Client.Client;
import com.example.demo.Client.Transaction;
import com.example.demo.PaymentMethod.IPaymentMethod;
import com.example.demo.PaymentMethod.payWithCash;
import com.example.demo.PaymentMethod.payWithWallet;

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
        put("mobile recharge", true);
        put("internet payment", true);
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

    public static boolean addDiscount(String discountType, String serviceName)
    {
    	boolean flag = false;
        if(discountType.equals("overall discount")) {
            overallDiscount = true;
            flag = true;
        }
        else if(discountType.equals("specific discount")) {
        	for(String service:services)
        	{
        		if(service.equals(serviceName))
        			flag = true;
        	}
        	if(flag)
        		specificDiscount.put(serviceName, true);
        }
        return flag;
    }

    public static Vector<String> checkDiscounts()
    {
        Vector<String> availableDiscounts = new Vector<>();
        if(overallDiscount)
            availableDiscounts.add("10% off as an overall discount!");
        for(Map.Entry<String, Boolean> entry:specificDiscount.entrySet()){
            if(entry.getValue())
                availableDiscounts.add("20% off for " +entry.getKey()+"!");
        }
        return availableDiscounts;
    }

    public abstract double calculateCost();

    public String pay(String payMethod)
    {
//         String ret = "";
        if(payMethod.equals("wallet"))
        {
//            ret += "transaction complete!\n";
            setPaymentMethod(new payWithWallet());
        }
        else if (payMethod.equals("cash"))
        {
            if (cashAvailable)
            {
//                ret +=  "transaction complete!\n";
                setPaymentMethod(new payWithCash());
            }
            else
            {
                // tell the user that they can't pay with cash
                return "Service doesn't accept cash on delivery\nCouldn't complete transaction";
            }
        }
        String ret = paymentMethod.pay(client, cost);
        if(!(ret.equals("insufficient money in your wallet")))
        {
            ret = "transaction complete!\n" + ret;
            Transaction transaction = new Transaction(client.getUsername(), cost);
            Transaction.paymentTransactions.add(transaction);
            return ret;
        }
        else
            return "Couldn't complete transaction due to insufficient money in your wallet";


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
