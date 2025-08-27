package com.courierx.tracking.services;

import java.util.List;

import com.courierx.tracking.model.Delivery;

public interface DeliveryService {
	
	public Delivery createDelivery(String username, Delivery d);
	public List<Delivery> getUserDeliveries(String username);
	public Delivery assignRider(Long deliveryId, String riderName);
	public Delivery updateStatus(Long deliveryId, String status);
	
}
