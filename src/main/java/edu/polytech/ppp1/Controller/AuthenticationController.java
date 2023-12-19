package edu.polytech.ppp1.Controller;

import edu.polytech.ppp1.dto.JwtAuthentificationResponse;
import edu.polytech.ppp1.dto.RefreshTokenRequest;
import edu.polytech.ppp1.dto.SignUpRequest;
import edu.polytech.ppp1.dto.SigninRequest;
import edu.polytech.ppp1.entities.User;
import edu.polytech.ppp1.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));

    }
    @PostMapping("/login")
    public  ResponseEntity<JwtAuthentificationResponse> signin(@RequestBody SigninRequest signinRequest){
        return  ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
    @PostMapping("/refresh")
    public  ResponseEntity<JwtAuthentificationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return  ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }


}
