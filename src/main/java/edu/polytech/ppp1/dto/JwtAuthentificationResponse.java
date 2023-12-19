package edu.polytech.ppp1.dto;

import lombok.Data;

@Data
public class JwtAuthentificationResponse {
    private String token;
    private String refreshToken;
}
