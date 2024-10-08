package com.mechachieve.vinculus.user;

public enum UserRole {
    ADMIN("admin"),
    USERTUTOR("usuario-tutor"),
    USERTUTORADO("usuario-tutorado");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}