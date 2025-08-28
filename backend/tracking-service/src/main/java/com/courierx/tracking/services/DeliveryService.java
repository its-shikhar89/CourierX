package com.courierx.tracking.services;

import java.util.List;

import com.courierx.tracking.model.Delivery;

public interface DeliveryService {
	
	public Delivery createDelivery(Long userId, Delivery d);
	public List<Delivery> getUserDeliveries(Long userId);
	public Delivery assignRider(Long deliveryId, Long riderId);
	public Delivery updateStatus(Long deliveryId, String status);
	
	public Object getDeliveryLocation(Long deliveryId);
}
