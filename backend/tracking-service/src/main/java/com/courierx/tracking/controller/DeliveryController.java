package com.courierx.tracking.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courierx.tracking.model.Delivery;
import com.courierx.tracking.services.DeliveryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
	private final DeliveryService service;
	
	@PostMapping
	public Delivery create(Authentication auth, @RequestBody Delivery d) {
		return service.createDelivery(auth.getName(), d);
	}
	
	@GetMapping
	public List<Delivery> myDeliveries(Authentication auth){
		return service.getUserDeliveries(auth.getName());
	}
	
	@PutMapping("/{id}/assign")
	public Delivery assignRider(@PathVariable Long id, @RequestParam String rider) {
		return service.assignRider(id, rider);
	}
	
	@PutMapping("/{id}/status")
	public Delivery updateStatuse(@PathVariable Long id, @RequestParam String status) {
		return service.updateStatus(id, status);
	}
}
