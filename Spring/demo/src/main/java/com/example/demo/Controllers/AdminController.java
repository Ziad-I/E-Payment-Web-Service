package com.example.demo.Controllers;

import java.io.IOException;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import com.example.demo.Admin.Admin;
import com.example.demo.Client.Transaction;
import com.example.demo.Refund.Refund;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
public class AdminController {
	
	private Admin admin;
	private boolean signedIn = false;
	
	@PostMapping("/admin/sign-in")
	public ResponseEntity<String> signIn(@RequestBody ObjectNode id) throws IOException
	{
		admin = new Admin();
		if(admin.signIn(id.get("id").asText()))
		{
			admin = new Admin(id.get("id").asInt());
			signedIn = true;
			return ResponseEntity.ok("signed in successfully!");
		}
		else
			return ResponseEntity.ok("invalid admin id");
	}
	
	@PostMapping("/admin/addDiscount")
	public ResponseEntity<String> addDiscount(@RequestBody ObjectNode discountInfo)
	{
		if (!signedIn)
            return ResponseEntity.ok("Please sign in first");
		if(admin.addDiscount(discountInfo.get("discountType").asText(), discountInfo.get("serviceName").asText()))
			return ResponseEntity.ok("discount added sucessfully!") ;
		else
			return ResponseEntity.ok("invalid input");
	}
	
	@PostMapping("/admin/transactions")
	public ResponseEntity checkTransactions(@RequestBody ObjectNode type)
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return ResponseEntity.ok(admin.checkTransactions(type.get("type").asText()));
	}
	
	
	@GetMapping("/admin/refunds")
	public ResponseEntity checkRefunds()
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return ResponseEntity.ok(admin.checkRefunds());
	}
	
	@GetMapping("/admin/refunds/accept/{idx}")
	public ResponseEntity acceptRefund(@PathVariable("idx") int idx)
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return (admin.acceptRefund(idx)) ? ResponseEntity.status(HttpStatus.ACCEPTED).build() 
				                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/admin/refunds/reject/{idx}")
	public ResponseEntity rejectRefund(@PathVariable("idx") int idx)
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return (admin.rejectRefund(idx)) ? ResponseEntity.status(HttpStatus.ACCEPTED).build() 
                                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
