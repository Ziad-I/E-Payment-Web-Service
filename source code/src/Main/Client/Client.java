package Main.Client;

import Main.Discounts.overallDiscount;
import Main.Discounts.specificDiscount;
import Main.Refund.Refund;
import Main.Services.Service;
import Main.Services.ServiceFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Client {

    private String username;
    private String password;
    Service service;
    Wallet wallet;

    public Client()
    {
        wallet = new Wallet();
    }

    public boolean signIn(String username, String password) throws IOException {
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
                System.out.println("successfully signed in!");
                return true;
            }
        }
        System.out.println("wrong username or password!");
        return false;
    }

    public boolean signUp(String username, String password) throws IOException {
        File file = new File("accounts.txt");
        file.createNewFile();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String data = sc.nextLine();
            String[] account = data.split(":");
            if(account[0].equals(username))
            {
                System.out.println("username already exists!");
                return false;
            }
        }
        System.out.println("successfully registered!");
        this.username = username;
        this.password = password;
        FileWriter fileWriter = new FileWriter("accounts.txt");
        fileWriter.write(username+":"+password);
        fileWriter.close();
        return true;
    }

    public void search(String serviceName)
    {
        if(Service.search(serviceName))
            System.out.println("service found");
        else
            System.out.println("service not found");
    }

    public void useService(String serviceName, String additional)
    {
        ServiceFactory serviceFactory = new ServiceFactory();
        service = serviceFactory.createService(serviceName, additional);
        service.setClient(this);
        if(!Service.discounts.isEmpty())
        {
            if(Service.discounts.get("overall discount"))
                service = new overallDiscount(service);
            if(service.getClass().getName().equals("MobileRecharge") && Service.discounts.get("specific discount"))
                service = new specificDiscount(service);
        }
        service.setCost(service.calculateCost());
        service.pay();
    }

    public void requestRefund()
    {
        if(service == null)
            System.out.println("please use a service first");
        else {
            Refund refund = new Refund(service, this);
            System.out.println("Refund requested");
        }
    }

    public void checkDiscount()
    {
        Service.checkDiscounts();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void addToWallet(double balance)
    {
        this.wallet.addBalance(balance);
    }
}


