package edu.polytech.ppp1.services;

import edu.polytech.ppp1.dto.JwtAuthentificationResponse;
import edu.polytech.ppp1.dto.RefreshTokenRequest;
import edu.polytech.ppp1.dto.SignUpRequest;
import edu.polytech.ppp1.dto.SigninRequest;
import edu.polytech.ppp1.entities.User;

public interface AuthenticationService {
     User signup(SignUpRequest signUpRequest);
     JwtAuthentificationResponse signin(SigninRequest signinRequest);
     JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
