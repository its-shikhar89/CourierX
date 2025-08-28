package com.courierx.tracking.services;

import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.courierx.tracking.model.Delivery;
import com.courierx.tracking.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
	
	private final DeliveryRepository repo;
	private final RestTemplate restTemplate;

	@Override
	public Delivery createDelivery(Long userId, Delivery d) {
		d.setUserId(userId);
		d.setStatus("PENDING");
		return repo.save(d);
	}

	@Override
	public List<Delivery> getUserDeliveries(Long userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public Delivery assignRider(Long deliveryId, Long riderId) {
		Delivery d  = repo.findById(deliveryId).orElseThrow(() -> new RuntimeException("Not Found"));
		d.setRiderId(riderId);
		d.setStatus("ASSIGNED");
		return repo.save(d);
	}

	@Override
	public Delivery updateStatus(Long deliveryId, String status) {
		Delivery d = repo.findById(deliveryId).orElseThrow(() -> new RuntimeException("Not Found"));
		d.setStatus(status);
		return repo.save(d);
	}
	
	@Override
	public Object getDeliveryLocation(Long deliveryId) {
        Delivery delivery = repo.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        if (delivery.getRiderId() == null) {
            throw new RuntimeException("No rider assigned yet!");
        }

        String url = "http://localhost:8082/riders/" + delivery.getRiderId() + "/location";

        ResponseEntity<Object> response =
                restTemplate.exchange(url, HttpMethod.GET, null, Object.class);

        return response.getBody();
    }
}
