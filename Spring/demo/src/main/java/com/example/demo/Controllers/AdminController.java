package com.example.demo.Controllers;

import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Admin.Admin;
import com.example.demo.Client.Transaction;
import com.example.demo.Refund.Refund;

public class AdminController {
	
	private Admin admin;
	
	
	@PostMapping(value = "/admin/addDiscount")
	public ResponseEntity<String> addDiscount(@RequestBody String discountType, @RequestBody String serviceName)
	{
		return (admin.addDiscount(discountType, serviceName)) ? ResponseEntity.ok("discount added sucessfully!") 
				 											  : ResponseEntity.status(404).build();
	}
	
	@PostMapping(value = "/admin/transactions")
	public ResponseEntity<Vector<Transaction>> checkTransactions(@RequestBody String type)
	{
		return ResponseEntity.ok(admin.checkTransactions(type));
	}
	
	
	@GetMapping(value = "/admin/refunds")
	public ResponseEntity<Vector<Refund>> checkRefunds()
	{
		return ResponseEntity.ok(admin.checkRefunds());
	}
	
	@GetMapping(value = "/admin/refunds/accept/{idx}")
	public ResponseEntity<Void> acceptRefund(@PathVariable("idx") int idx)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping(value = "/admin/refunds/reject/{idx}")
	public ResponseEntity<Void> rejectRefund(@PathVariable("idx") int idx)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
}
