package edu.polytech.ppp1.services;

import edu.polytech.ppp1.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
      String extractUserName(String token);
     String generateToken(UserDetails userDetails);
     boolean isTokenValid(String token,UserDetails userDetails);
    String generaterefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);

}
