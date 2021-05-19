package com.example.assig3.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
