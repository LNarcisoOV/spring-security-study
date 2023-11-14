package com.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.security.enumeration.UserRole;
import com.security.model.User;

@Service
public class AuthorizationService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return "admin".equals(username) ? new User(1, "admin", "123", UserRole.ADMIN) : new User(2, "user", "123", UserRole.USER);
    }

}
