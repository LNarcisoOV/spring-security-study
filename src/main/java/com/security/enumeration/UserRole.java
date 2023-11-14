package com.security.enumeration;

public enum UserRole {

    ADMIN("Admin"),
    USER("User");
    
    private String role;
    
    UserRole(String role) {
        this.role = role;
    }
    
    private String getRole() {
        return this.role;
    }
}
