package com.courierx.auth.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courierx.auth.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public String signup(@RequestBody Map<String, String> body) {
		return authService.signup(body.get("username"), body.get("password"), body.get("role"));
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> body) {
		return authService.login(body.get("username"), body.get("password"));
	}
}
