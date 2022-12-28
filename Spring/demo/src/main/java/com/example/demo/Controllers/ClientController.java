package com.example.demo.Controllers;

import com.example.demo.Client.Client;
import com.example.demo.Services.Service;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Vector;


@RestController
public class ClientController {

    private Client client;
    private boolean  signedIn =  false;


    @PostMapping("client/sign-in")
    public ResponseEntity<String> signIn(@RequestBody String username,@RequestBody String password) throws IOException
    {
        // use the Client class to sign in the user
        client = new Client();
        String ret = client.signIn(username, password);
        if(ret == "successfully signed in!")
            signedIn = true;
        return ResponseEntity.ok(ret);
    }


    @PostMapping("client/sign-up")
    public ResponseEntity<String> signUp(@RequestBody String username,@RequestBody String password) throws IOException
    {
        // use the Client class to sign in the user
        client = new Client();
        String ret = client.signUp(username, password);
        if(ret == "successfully registered!")
            signedIn = true;
        return ResponseEntity.ok(ret);
    }

    @GetMapping(value = "client/search") // url: client/search?serviceName=""
    public ResponseEntity<String> Search(@RequestParam("serviceName") String serviceName)
    {
        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");

        String ret = client.search(serviceName);
        return ResponseEntity.ok(ret);
    }



    @PostMapping(value = "client/request-refund")
    public ResponseEntity<String>requestRefund(@RequestBody Service service)
    {
        return ResponseEntity.ok(client.requestRefund(service));
    }
    @PostMapping(value = "client/addToWaller")
    public ResponseEntity<String> addToWallet(@RequestBody double balance)
    {
        if (!signedIn)
            return ResponseEntity.ok("Please login/register first");

        String ret = client.addToWallet(balance);
        return ResponseEntity.ok(ret);
    }


    @PostMapping(value = "client/check-discounts")
    public ResponseEntity<Vector<String>> checkDiscount()
    {
        return ResponseEntity.ok(client.checkDiscount());
    }
}
