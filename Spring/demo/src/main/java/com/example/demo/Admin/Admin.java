package com.example.demo.Admin;

import com.example.demo.Client.Transaction;
import com.example.demo.Refund.Refund;
import com.example.demo.Services.Service;
import com.example.demo.Providers.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Admin {

    private int id;
    
    public Admin()
    {
    	
    }
    
    public Admin(int id)
    {
    	this.id = id;
    }
    
    public boolean signIn(String id) throws IOException {
        File file = new File("admin.txt");
        file.createNewFile();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String data = sc.nextLine();
            if(id.equals(data))
            {
                this.id = Integer.parseInt(id);
                return true;

            }
        }
        return false;
    }

    public Vector<Refund> checkRefunds()
    {
        return Refund.refunds;
    }
    
    public boolean acceptRefund(int idx)
    {
    	Vector<Refund> refunds = Refund.refunds;
    	if(!(idx >= 0 && idx < refunds.size()))
    		return false;
    	refunds.get(idx).setAccepted(true);
        refunds.get(idx).returnMoney();

        Transaction transaction = new Transaction(refunds.get(idx).getService().getClient().getUsername(),
                refunds.get(idx).getService().getCost());
        Transaction.refundTransactions.add(transaction);
        Refund.refunds.remove(idx);
        return true;
    }
    
    public boolean rejectRefund(int idx)
    {
    	Vector<Refund> refunds = Refund.refunds;
    	if(!(idx >= 0 && idx < refunds.size()))
    		return false;
    	refunds.get(idx).setAccepted(false);
    	return true;
    }

    public boolean addDiscount(String discountType, String serviceName)
    {
        return Service.addDiscount(discountType, serviceName);
    }

    public Vector<Transaction> checkTransactions(String type)
    {
        if (type.equals("payment"))
            return Transaction.paymentTransactions;
        else if(type.equals("add to wallet"))
            return Transaction.addToWalletTransactions;
        else if(type.equals("refund"))
            return Transaction.refundTransactions;
        return null;
    }

    public String addProvider(ObjectNode objNode)
    {
        String serviceName = objNode.get("service name").asText();

        if(serviceName.equals("mobile recharge")) {
            String providerName = objNode.get("provider name").asText();
            mobileRechargeProvider.providers.add(providerName);
        }
        else if(serviceName.equals("internet payment")) {
            String providerName = objNode.get("provider name").asText();
            internetPaymentProvider.providers.add(providerName);
        }
        else if(serviceName.equals("donations")) {
            String destinationName = objNode.get("destination name").asText();
            DonationProvider.destinations.add(destinationName);
        }
        else if(serviceName.equals("landline"))
        {
            String receiptType = objNode.get("receipt type").asText();
            double cost = objNode.get("cost").asDouble();
            landlineProvider.receipts.put(receiptType, cost);
        }
        else
            return "Invalid service name";
        return "Provider added successfully";
    }
}
