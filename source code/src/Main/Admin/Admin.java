package Main.Admin;

import Main.Client.Transaction;
import Main.Refund.Refund;
import Main.Services.Service;

import java.util.Scanner;
import java.util.Vector;

public class Admin {

    private final int id;

    public Admin(int id)
    {
        this.id = id;
    }

    public void checkRefunds()
    {
        Vector<Refund> refunds = Refund.refunds;

        int sz = Refund.refunds.size();
        for(int i = 0; i < sz; i++)
        {
            System.out.println("service name: " + refunds.get(i).getService().getClass().getName());
            System.out.println("client's username: " + refunds.get(i).getClient().getUsername());
            System.out.println("service cost to be refunded: " + refunds.get(i).getService().getCost());
            System.out.println("service id: " + refunds.get(i).getService().getServiceID());

            System.out.println("Do you want to accept this refund Y/N?");
            Scanner sc = new  Scanner(System.in);
            String ch = sc.next();

            if(ch.equals("Y"))
            {
                System.out.println("Request Accepted");
                refunds.get(i).setAccepted(true);
                refunds.get(i).returnMoney();

                Transaction transaction = new Transaction(refunds.get(i).getClient().getUsername(),
                        refunds.get(i).getService().getCost());
                Transaction.refundTransactions.add(transaction);
            }
            else {
                System.out.println("Request rejected");
                refunds.get(i).setAccepted(false);
            }
        }
        Refund.refunds.clear();
    }

    public void addDiscount(String discountType) {
        Service.addDiscount(discountType);
    }


    public Vector<Transaction> checkTransactions(int type)
    {
        if (type == 1)
            return Transaction.paymentTransactions;
        else if(type == 2)
            return Transaction.addToWalletTransactions;
        else
            return Transaction.refundTransactions;
    }
}
