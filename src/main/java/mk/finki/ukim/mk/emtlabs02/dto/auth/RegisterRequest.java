package mk.finki.ukim.mk.emtlabs02.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}