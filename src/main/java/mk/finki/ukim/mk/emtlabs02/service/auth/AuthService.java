package mk.finki.ukim.mk.emtlabs02.service.auth;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.emtlabs02.dto.auth.AuthResponse;
import mk.finki.ukim.mk.emtlabs02.dto.auth.LoginRequest;
import mk.finki.ukim.mk.emtlabs02.dto.auth.RegisterRequest;
import mk.finki.ukim.mk.emtlabs02.model.Role;
import mk.finki.ukim.mk.emtlabs02.model.User;
import mk.finki.ukim.mk.emtlabs02.repository.UserRepository;
import mk.finki.ukim.mk.emtlabs02.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}