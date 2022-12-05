package Main;

import Main.Admin.Admin;
import Main.Client.Client;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void invalid() {
        System.out.println("invalid input");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("1- login as client\n2- login as admin");
            int choice = sc.nextInt();

            // client part
            if (choice == 1)
            {
                System.out.println("1. sign in\n2. sign up");
                Client client = new Client();

                choice = sc.nextInt();
                if (choice == 1)
                {
                    System.out.println("Enter username and password");
                    System.out.print("username: ");
                    String username = sc.next();
                    System.out.print("password: ");
                    String password = sc.next();
                    if (!client.signIn(username, password))
                        continue;
                }
                else if (choice == 2)
                {
                    System.out.println("Enter username and password");
                    System.out.print("username: ");
                    String username = sc.next();
                    System.out.print("password: ");
                    String password = sc.next();

                    if (!client.signUp(username, password))
                        continue;
                }
                else
                    invalid();

                while (true)
                {
                    System.out.println("1. Search for a service\n2. Use a service\n3. Ask for a refund\n4. Deposit to wallet\n5. check discount\n6. sign out");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        sc.nextLine();
                        System.out.println("Search for: ");
                        String data = sc.nextLine();
                        client.search(data);
                    } else if (choice == 2) {
                        System.out.println("1.Mobile recharge\n2.Internet payment\n3. Landline\n4.Donate");
                        choice = sc.nextInt();
                        if (choice == 1) {
                            System.out.println("1.Orange\n2.Etisalat\n3.Vodafone\n4.We");
                            choice = sc.nextInt();
                            if (choice == 1) {
                                client.useService("mobile recharge", "orange");
                            } else if (choice == 2) {
                                client.useService("mobile recharge", "etisalat");
                            } else if (choice == 3) {
                                client.useService("mobile recharge", "vodafone");
                            } else if (choice == 4) {
                                client.useService("mobile recharge", "we");
                            } else
                                invalid();
                        } else if (choice == 2) {
                            System.out.println("1. Orange\n2. Etisalat\n 3. Vodafone\n 4.We");
                            choice = sc.nextInt();
                            if (choice == 1) {
                                client.useService("internet payment", "orange");
                            } else if (choice == 2) {
                                client.useService("internet payment", "etisalat");
                            } else if (choice == 3) {
                                client.useService("internet payment", "vodafone");
                            } else if (choice == 4) {
                                client.useService("internet payment", "we");
                            } else
                                invalid();
                        } else if (choice == 3) {
                            System.out.println("1. Monthly receipt\n2. Quarterly receipt\n ");
                            choice = sc.nextInt();
                            if (choice == 1) {
                                client.useService("landline", "monthly receipt");

                            } else if (choice == 2) {
                                client.useService("landline", "quarterly receipt");
                            } else
                                invalid();
                        } else if (choice == 4) {
                            System.out.println("1. Cancer hospitals\n2. Schools\n 3. NGOs");
                            choice = sc.nextInt();
                            if (choice == 1) {
                                client.useService("donations", "cancer hospitals");

                            } else if (choice == 2) {
                                client.useService("donations", "schools");
                            } else if (choice == 3) {
                                client.useService("donations", "NGOs");
                            } else
                                invalid();
                        } else
                            invalid();


                    } else if (choice == 3)
                        client.requestRefund();
                    else if (choice == 4) {
                        System.out.println("How much would you like to deposit?: ");
                        double money = sc.nextDouble();
                        client.addToWallet(money);
                    } else if (choice == 5) {
                        client.checkDiscount();
                    } else if(choice == 6)
                        break;
                    else
                        invalid();
                }
            }
            // admin part
            else if (choice == 2)
            {
                Admin admin = null;
                System.out.println("enter admin's id: ");
                int id = sc.nextInt();
                File adminIDs = new File("admin.txt");

                Scanner adminSC = new Scanner(adminIDs);
                while (adminSC.hasNextLine()) {
                    String data = adminSC.next();
                    if (Integer.parseInt(data) == id) {
                        admin = new Admin(id);
                        System.out.println("successfully signed in as an admin!");
                    }
                }
                if (admin == null)
                    invalid();

                while(true)
                {
                    System.out.println("1- add discount\n2- check refunds\n3- sign out");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println("1- overall discount\n2- specific discount");
                        choice = sc.nextInt();
                        if (choice == 1) {
                            admin.addDiscount("overall discount");
                            System.out.println("successfully added!");
                        }
                        else if (choice == 2) {
                            admin.addDiscount("specific discount");
                            System.out.println("successfully added!");
                        }
                        else
                            invalid();
                    } else if (choice == 2) {
                        admin.checkRefunds();
                    } else if(choice == 3)
                        break;
                    else
                        invalid();
                }
            }
            else
                invalid();
        }

    }
}
