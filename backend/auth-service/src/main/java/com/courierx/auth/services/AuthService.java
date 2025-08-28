package com.courierx.auth.services;

import com.courierx.auth.dto.LoginResponse;

public interface AuthService {
	public String signup(String username, String password, String role);
	public LoginResponse login(String username, String password);
}
