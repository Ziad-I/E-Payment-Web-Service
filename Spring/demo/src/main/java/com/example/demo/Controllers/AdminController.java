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
	
	private Admin admin = new Admin();
	private boolean signedIn = false;
	
	@PostMapping("/admin/sign-in")
	public ResponseEntity<String> signIn(@RequestBody ObjectNode id) throws IOException
	{
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
	
	@GetMapping("/admin/transactions/{type}")
	public ResponseEntity checkTransactions(@PathVariable("type") String type)
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return ResponseEntity.ok(admin.checkTransactions(type));
	}
	
	
	@GetMapping("/admin/refunds")
	public ResponseEntity checkRefunds()
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return ResponseEntity.ok(admin.checkRefunds());
	}
	
	@PostMapping("/admin/refunds/accept/{idx}")
	public ResponseEntity<String> acceptRefund(@PathVariable("idx") int idx)
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return (admin.acceptRefund(idx)) ? ResponseEntity.ok("refund accepted successfully") 
				                         : ResponseEntity.ok("refund does not exist");
	}
	
	@PostMapping("/admin/refunds/reject/{idx}")
	public ResponseEntity<String> rejectRefund(@PathVariable("idx") int idx)
	{
		if (!signedIn)
			return ResponseEntity.ok("Please sign in first");
		return (admin.rejectRefund(idx)) ? ResponseEntity.ok("refund rejected successfully") 
                						 : ResponseEntity.ok("refund does not exist");
	}
	
}
