package com.courierx.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courierx.tracking.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
//	List<Delivery> findByDeliveryId(Long deliveryId);
	
	 // Custom finder method to get all deliveries of a particular user
    List<Delivery> findByUserId(Long userId);

    // Optional: if you want to fetch delivery by riderId
    List<Delivery> findByRiderId(Long riderId);
}
