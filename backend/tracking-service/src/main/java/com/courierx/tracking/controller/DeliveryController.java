package com.courierx.tracking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public Delivery createDelivery(Authentication auth, @RequestBody Delivery delivery) {
		Long userId = (Long) auth.getPrincipal();
		delivery.setUserId(null);
		return service.createDelivery(userId, delivery);
	}

	@GetMapping
	public List<Delivery> myDeliveries(Authentication auth) {
		Long userId = (Long) auth.getPrincipal();
		return service.getUserDeliveries(userId);
	}

	@PutMapping("/{deliveryId}/assign")
	public Delivery assignRider(@PathVariable Long deliveryId, @RequestParam Long riderId) {
		return service.assignRider(deliveryId, riderId);
	}

	@PutMapping("/{id}/status")
	public Delivery updateStatuse(@PathVariable Long id, @RequestParam String status) {
		return service.updateStatus(id, status);
	}

	@GetMapping("/{deliveryId}/location")
	public Object getDeliveryLocation(@PathVariable Long deliveryId) {
		return service.getDeliveryLocation(deliveryId);
	}
}
