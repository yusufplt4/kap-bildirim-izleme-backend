package com.example.kap.service.impl;

import com.example.kap.dto.request.LoginRequest;
import com.example.kap.dto.request.RegisterRequest;
import com.example.kap.dto.response.LoginResponse;
import com.example.kap.entity.User;
import com.example.kap.repository.UserRepository;
import com.example.kap.service.AuthenticationService;
import com.example.kap.service.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String register(RegisterRequest request) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Kullanıcı bilgileri boş olamaz."
            );
        }

        if (request.getUsername() == null
                || request.getUsername().isBlank()) {

            throw new IllegalArgumentException(
                    "Kullanıcı adı boş olamaz."
            );
        }

        if (request.getPassword() == null
                || request.getPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "Şifre boş olamaz."
            );
        }

        String username = request.getUsername().trim();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException(
                    "Bu kullanıcı adı zaten kullanılıyor."
            );
        }

        String role =
                request.getRole() == null
                        || request.getRole().isBlank()
                        ? "USER"
                        : request.getRole()
                        .trim()
                        .toUpperCase(Locale.ROOT);

        if (!role.equals("USER") && !role.equals("ADMIN")) {
            throw new IllegalArgumentException(
                    "Geçersiz kullanıcı rolü."
            );
        } // Sistemde yalnızca USER ve ADMIN rollerinin oluşturulmasına izin verir.

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User(
                username,
                encodedPassword,
                role
        );

        userRepository.save(user);

        return "Kullanıcı başarıyla kaydedildi.";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Giriş bilgileri boş olamaz."
            );
        }

        if (request.getUsername() == null
                || request.getUsername().isBlank()
                || request.getPassword() == null
                || request.getPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "Kullanıcı adı veya şifre hatalı."
            );
        }

        String username = request.getUsername().trim();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Kullanıcı adı veya şifre hatalı."
                        )
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                ); // Açık şifreyi DB'deki BCrypt hash değeriyle karşılaştırır.

        if (!passwordMatches) {
            throw new IllegalArgumentException(
                    "Kullanıcı adı veya şifre hatalı."
            );
        }

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new LoginResponse(token);
    }
}