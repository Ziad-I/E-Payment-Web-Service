package com.example.demo.Admin;

import com.example.demo.Client.Transaction;
import com.example.demo.Refund.Refund;
import com.example.demo.Services.Service;

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
}
