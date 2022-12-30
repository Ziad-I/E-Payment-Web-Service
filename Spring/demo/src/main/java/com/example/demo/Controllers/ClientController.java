package com.example.demo.Controllers;

import com.example.demo.Client.Client;
import com.example.demo.Services.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Vector;


@RestController
public class ClientController {

    private Client client;
    private boolean  signedIn =  false;


    @PostMapping("/client/sign-in")
    public ResponseEntity<String> signIn(@RequestBody ObjectNode objNode) throws IOException
    {
        // use the Client class to sign in the user
        if(signedIn)
            return ResponseEntity.ok("You are already signed in");
        client = new Client();
        String username = objNode.get("username").asText();
        String password = objNode.get("password").asText();
        String ret = client.signIn(username, password);
        if(ret == "successfully signed in!")
        {
            signedIn = true;
            ret += "\n Hello, " + username;
        }
        return ResponseEntity.ok(ret);
    }


    @PostMapping("/client/sign-up")
    public ResponseEntity<String> signUp(@RequestBody ObjectNode objNode) throws IOException
    {
        // use the Client class to sign in the user
        if(signedIn)
            return ResponseEntity.ok("You are already signed in");
        client = new Client();
        String username = objNode.get("username").asText();
        String password = objNode.get("password").asText();
        String ret = client.signUp(username, password);
        if(ret == "successfully registered!")
        {
            signedIn = true;
            ret += "\nHello, " + username;
        }
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/client/search") // url: client/search?serviceName=""
    public ResponseEntity<String> Search(@RequestParam("serviceName") String serviceName)
    {
        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");

        serviceName= serviceName.replace('-', ' ');
        String ret = client.search(serviceName);
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/client/use-service")
    public ResponseEntity<String> useService(@RequestBody ObjectNode objNode)
    {
        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");

        String serviceName = objNode.get("service name").asText();
        String additional = objNode.get("additional").asText();
        String payMethod = objNode.get("payment method").asText();
        double amount = 0;
        if(!(serviceName.equals("landline")))
            amount  = objNode.get("amount").asDouble();
        String ret = client.useService(serviceName, additional, payMethod, amount);
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/client/request-refund")
    public ResponseEntity<String>requestRefund(@RequestBody ObjectNode objNode)
    {
        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");

        int serviceID = objNode.get("service id").asInt();
        String ret = client.requestRefund(serviceID);
        return ResponseEntity.ok(ret);
    }


    @PostMapping("/client/add-to-wallet")
    public ResponseEntity<String> addToWallet(@RequestBody ObjectNode objNode)
    {
        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");
        double balance = objNode.get("balance").asDouble();

        String ret = client.addToWallet(balance);
        return ResponseEntity.ok(ret);
    }


    @GetMapping("/client/check-discounts")
    public ResponseEntity checkDiscount()
    {

        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");


        Vector<String> ret = client.checkDiscount();
        if(ret.size() == 0)
            ret.add("No available discounts");
        return ResponseEntity.ok(ret);
    }


    @GetMapping(value = "/client/sign-out")
    public ResponseEntity<String> signOut()
    {
        if(!signedIn)
            return ResponseEntity.ok("You are not signed in");
        this.client = null;
        signedIn = false;
        return ResponseEntity.ok("Successfully signed out");
    }
}
