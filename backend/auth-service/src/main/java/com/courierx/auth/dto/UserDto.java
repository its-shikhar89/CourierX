package com.courierx.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
	private Long id;
	private String username;
	private String role;
	
	public UserDto(Long id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
	}
}
