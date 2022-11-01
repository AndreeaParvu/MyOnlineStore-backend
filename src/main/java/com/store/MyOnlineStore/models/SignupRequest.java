package com.store.MyOnlineStore.models;

import com.store.MyOnlineStore.domain.entities.CommerceRole;

public class SignupRequest {
    private String email;
    private String password;
    private CommerceRole role;

    public SignupRequest() {}

    public SignupRequest(String email, String password, CommerceRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CommerceRole getRole() {
        return role;
    }

    public void setRole(CommerceRole role) {
        this.role = role;
    }
}
