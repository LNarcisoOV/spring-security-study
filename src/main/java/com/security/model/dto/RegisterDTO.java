package com.security.model.dto;

import com.security.enumeration.UserRole;

public record RegisterDTO(String username, String password, UserRole userRole) {

}
