package com.example.demo.Controllers;

import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Admin.Admin;
import com.example.demo.Client.Transaction;
import com.example.demo.Refund.Refund;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
public class AdminController {
	
	private Admin admin;
	
	public AdminController()
	{
		admin = new Admin(1);
	}
	
	
	@PostMapping("/admin/addDiscount")
	public ResponseEntity<String> addDiscount(@RequestBody ObjectNode discountInfo)
	{
		if(admin.addDiscount(discountInfo.get("discountType").asText(), discountInfo.get("serviceName").asText()))
			return ResponseEntity.ok("discount added sucessfully!") ;
		else
			return ResponseEntity.ok("invalid input");
	}
	
	@PostMapping("/admin/transactions")
	public ResponseEntity<Vector<Transaction>> checkTransactions(@RequestBody String type)
	{
		return ResponseEntity.ok(admin.checkTransactions(type));
	}
	
	
	@GetMapping("/admin/refunds")
	public ResponseEntity<Vector<Refund>> checkRefunds()
	{
		return ResponseEntity.ok(admin.checkRefunds());
	}
	
	@GetMapping("/admin/refunds/accept/{idx}")
	public ResponseEntity<Void> acceptRefund(@PathVariable("idx") int idx)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping("/admin/refunds/reject/{idx}")
	public ResponseEntity<Void> rejectRefund(@PathVariable("idx") int idx)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
}
