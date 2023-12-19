package edu.polytech.ppp1.dto;

import edu.polytech.ppp1.entities.Role;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
