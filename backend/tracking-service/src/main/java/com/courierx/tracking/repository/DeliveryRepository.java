package com.courierx.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courierx.tracking.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	List<Delivery> findByUsername(String username);
}
