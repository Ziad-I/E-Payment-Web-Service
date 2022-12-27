package Main.Client;

public class creditCard {

    private String number;

    public creditCard(String number) {
        this.number = number;
    }

    public creditCard() {
        this.number = "0000-0000-0000-0000";
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean completeTransaction()
    {
        /*
        communicate with credit card's bank and withdraw money from it to add to the user wallet
         */
       return true;
    }


}
