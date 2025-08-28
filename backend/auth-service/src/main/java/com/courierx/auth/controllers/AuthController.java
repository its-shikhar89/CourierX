package com.courierx.auth.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courierx.auth.dto.LoginResponse;
import com.courierx.auth.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/signup")
	public String signup(@RequestBody Map<String, String> body) {
		return authService.signup(body.get("username"), body.get("password"), body.get("role"));
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody Map<String, String> body) {
		return authService.login(body.get("username"), body.get("password"));
	}
}
