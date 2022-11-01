package com.store.MyOnlineStore.controller;

import com.store.MyOnlineStore.models.JwtResponse;
import com.store.MyOnlineStore.security.SecurityUserDetails;
import com.store.MyOnlineStore.security.jwt.JWTUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("commerce/api/user/")
public class UserController {

    private final JWTUtils jwtUtils;

    public UserController(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public ResponseEntity<JwtResponse> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails currentUser = (SecurityUserDetails) authentication;

        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = currentUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), currentUser.getUsername(), roles));
    }
}
