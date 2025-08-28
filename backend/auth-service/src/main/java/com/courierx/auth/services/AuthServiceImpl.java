package com.courierx.auth.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.courierx.auth.dto.LoginResponse;
import com.courierx.auth.dto.UserDto;
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
	public LoginResponse login(String username, String password) {
		var user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("Invalid username or password!"));
	
		if(!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}
		String token = jwtUtil.generateToken(user);
		UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getRole());
		
		return new LoginResponse(token, userDto);
	}

}
