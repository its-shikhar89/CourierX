package com.courierx.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse {
	private String token;
	private UserDto userDto;
	
	public LoginResponse(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }
}
