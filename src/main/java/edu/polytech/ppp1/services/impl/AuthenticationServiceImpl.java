package edu.polytech.ppp1.services.impl;

import edu.polytech.ppp1.dto.JwtAuthentificationResponse;
import edu.polytech.ppp1.dto.RefreshTokenRequest;
import edu.polytech.ppp1.dto.SignUpRequest;
import edu.polytech.ppp1.dto.SigninRequest;
import edu.polytech.ppp1.entities.Role;
import edu.polytech.ppp1.entities.User;
import edu.polytech.ppp1.repository.UserRepository;
import edu.polytech.ppp1.services.AuthenticationService;
import edu.polytech.ppp1.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }
    public JwtAuthentificationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password!"));
        var jwt=jwtService.generateToken(user);
        var refreshToken=jwtService.generaterefreshToken(new HashMap<>(),user);
        JwtAuthentificationResponse jwtAuthentificationResponse=new JwtAuthentificationResponse();
        jwtAuthentificationResponse.setToken(jwt);
        jwtAuthentificationResponse.setRefreshToken(refreshToken);
        return jwtAuthentificationResponse;
    }
    public JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthentificationResponse jwtAuthentificationResponse=new JwtAuthentificationResponse();
            jwtAuthentificationResponse.setToken(jwt);
            jwtAuthentificationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthentificationResponse;
        }
        return null;

    }
}
