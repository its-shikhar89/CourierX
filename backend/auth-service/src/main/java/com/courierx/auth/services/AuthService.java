package com.courierx.auth.services;

public interface AuthService {
	public String signup(String username, String password, String role);
	public String login(String username, String password);
}
