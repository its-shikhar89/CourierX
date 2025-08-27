package com.courierx.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsProvider {
    UserDetails loadUserByUsername(String username);
}
