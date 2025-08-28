package com.courierx.auth.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.courierx.auth.model.User;
import com.courierx.auth.repository.UserRepository;
import com.courierx.auth.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepo;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder encoder;

	@Override
	public String signup(String username, String password, String role) {
		userRepo.findByUsername(username).ifPresent(u -> {
			throw new RuntimeException("User already exists");
		});
		
		var user = User.builder()
				.username(username)
				.password(encoder.encode(password))
				.role(role)
				.build();
		userRepo.save(user);
		
		return "User registered successfully!";
	}

	@Override
	public String login(String username, String password) {
		var user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("Invalid username or password!"));
	
		if(!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}
		
		return jwtUtil.generateToken(user);
	}

}
