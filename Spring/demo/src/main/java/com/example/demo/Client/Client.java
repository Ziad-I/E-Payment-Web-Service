package com.example.demo.Client;

import com.example.demo.Discounts.overallDiscount;
import com.example.demo.Discounts.specificDiscount;
import com.example.demo.Refund.Refund;
import com.example.demo.Services.Service;
import com.example.demo.Services.ServiceFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Client {

    private String username;
    private String password;
    private Vector<Service> serviceHistory = new Vector<>();
    private Wallet wallet;
    private CreditCard card;

    public Client()
    {
        wallet = new Wallet();
        card = new CreditCard();
    }


    // in API
    public String signIn(String username, String password) throws IOException {
        File file = new File("accounts.txt");
        file.createNewFile();
        String account = username + ":" + password;
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String data = sc.nextLine();
            if(account.equals(data))
            {
                this.username = username;
                this.password = password;
                return "successfully signed in!";

            }
        }
        return "wrong username or password!";
    }

    // in API
    public String signUp(String username, String password) throws IOException {
        File file = new File("accounts.txt");
        file.createNewFile();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String data = sc.nextLine();
            String[] account = data.split(":");
            if(account[0].equals(username))
            {
                return "username already exists!";
            }
        }
        this.username = username;
        this.password = password;
        FileWriter fileWriter = new FileWriter("accounts.txt");
        fileWriter.write(username+":"+password);
        fileWriter.close();
        return "successfully registered!";
    }

    // in API
    public String search(String serviceName)
    {
        if(Service.search(serviceName))
            return "service "+ serviceName+" found";
        else
            return "service "+ serviceName+" not found";
    }

    // in API
    public String useService(String serviceName, String additional, String paymentMethod, double amount)
    {
        ServiceFactory serviceFactory = new ServiceFactory();
        Service service = serviceFactory.createService(serviceName, additional, amount);
        service.setClient(this);
        if(Service.overallDiscount && serviceHistory.isEmpty())
                service = new overallDiscount(service);
        if(Service.specificDiscount.get(serviceName))
                service = new specificDiscount(service);
        service.setCost(service.calculateCost());
        service.setClient(this);
        String ret = service.pay(paymentMethod);
        serviceHistory.add(service);
        return ret;
    }

    public String requestRefund(Service service)
    {
        if(serviceHistory.isEmpty())
            return "Please use a service first";
        else {
            Refund refund = new Refund(service, this);
             return "Refund requested";
        }
    }

    // in API
    public String addToWallet(double balance) {
        if(card.withdrawFromCard(balance)) {
            double newBalance = this.wallet.addBalance(balance);
            Transaction transaction = new Transaction(this.username, balance);
            Transaction.addToWalletTransactions.add(transaction);
            return "Added to wallet successfully\nYour new balance is: "+ newBalance;
        }
        else
            return "Failed to add to wallet";
            // failed
    }

    public Vector<String> checkDiscount()
    {
        return Service.checkDiscounts();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }


}


