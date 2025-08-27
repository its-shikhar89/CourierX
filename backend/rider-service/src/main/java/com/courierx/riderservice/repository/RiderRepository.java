package com.courierx.riderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courierx.riderservice.entity.Rider;

public interface RiderRepository extends JpaRepository<Rider, Long> {

}
