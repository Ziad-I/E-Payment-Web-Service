package com.example.demo.Client;

public class CreditCard {

    private String number;

    public CreditCard(String number) {
        this.number = number;
    }

    public CreditCard() {
        this.number = "0000-0000-0000-0000";
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean withdrawFromCard(double balance)
    {
        /*
        communicate with credit card's bank and withdraw money from it to add to the user wallet
         */
       return true;
    }


}
