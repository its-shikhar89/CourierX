package com.courierx.tracking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.courierx.tracking.model.Delivery;
import com.courierx.tracking.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
	
	private final DeliveryRepository repo;

	@Override
	public Delivery createDelivery(String username, Delivery d) {
		d.setUsername(username);
		d.setStatus("PENDING");
		return repo.save(d);
	}

	@Override
	public List<Delivery> getUserDeliveries(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public Delivery assignRider(Long deliveryId, String riderName) {
		Delivery d  = repo.findById(deliveryId).orElseThrow(() -> new RuntimeException("Not Found"));
		d.setRidername(riderName);
		d.setStatus("ASSIGNED");
		return repo.save(d);
	}

	@Override
	public Delivery updateStatus(Long deliveryId, String status) {
		Delivery d = repo.findById(deliveryId).orElseThrow(() -> new RuntimeException("Not Found"));
		d.setStatus("ACCEPTED");
		return repo.save(d);
	}

}
