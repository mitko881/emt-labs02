package mk.finki.ukim.mk.emtlabs02.controller.auth;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.emtlabs02.dto.auth.AuthResponse;
import mk.finki.ukim.mk.emtlabs02.dto.auth.LoginRequest;
import mk.finki.ukim.mk.emtlabs02.dto.auth.RegisterRequest;
import mk.finki.ukim.mk.emtlabs02.service.auth.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}