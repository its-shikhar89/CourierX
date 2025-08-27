package com.courierx.riderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courierx.riderservice.entity.RiderLocation;

public interface RiderLocationRepository extends JpaRepository<RiderLocation, Long> {

}
