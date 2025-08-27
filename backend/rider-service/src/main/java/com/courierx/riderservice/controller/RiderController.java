package com.courierx.riderservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courierx.riderservice.entity.Rider;
import com.courierx.riderservice.entity.RiderLocation;
import com.courierx.riderservice.repository.RiderLocationRepository;
import com.courierx.riderservice.repository.RiderRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/riders")
@RequiredArgsConstructor
public class RiderController {
	
	private final RiderRepository riderRepo;
	private final RiderLocationRepository locationRepo;
	
	@PostMapping
	public Rider createRider(@RequestBody Rider rider) {
		rider.setActive(true);
		return riderRepo.save(rider);
	}
	
	@GetMapping
	public List<Rider> getAllRiders(){
		return riderRepo.findAll();
	}
	
	@PostMapping("/{riderId}/location")
	public RiderLocation updateLocation(@PathVariable Long riderId, @RequestBody RiderLocation location) {
		Rider rider = riderRepo.findById(riderId).orElseThrow(() -> new RuntimeException("Rider not found"));
		location.setRider(rider);
		location.setTimestamp(LocalDateTime.now());
		return locationRepo.save(location);
	}
	
	@GetMapping("/{riderId}/locations")
	public List<RiderLocation> getRiderLocations(@PathVariable Long riderId){
		return locationRepo.findAll()
				.stream()
				.filter(loc -> loc.getRider().getId().equals(riderId))
				.toList();
	}
}
