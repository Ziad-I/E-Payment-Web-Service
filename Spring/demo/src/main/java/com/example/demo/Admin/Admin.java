package com.example.demo.Admin;

import com.example.demo.Client.Transaction;
import com.example.demo.Refund.Refund;
import com.example.demo.Services.Service;

import java.util.Scanner;
import java.util.Vector;

public class Admin {

    private final int id;

    public Admin(int id)
    {
        this.id = id;
    }

    public Vector<Refund> checkRefunds()
    {
        return Refund.refunds;
    }
    
    public void acceptRefund(int idx)
    {
    	Vector<Refund> refunds = Refund.refunds;
    	refunds.get(idx).setAccepted(true);
        refunds.get(idx).returnMoney();

        Transaction transaction = new Transaction(refunds.get(idx).getClient().getUsername(),
                refunds.get(idx).getService().getCost());
        Transaction.refundTransactions.add(transaction);
        Refund.refunds.remove(idx);
    }
    
    public void rejectRefund(int idx)
    {
    	Vector<Refund> refunds = Refund.refunds;
    	refunds.get(idx).setAccepted(false);
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
}
