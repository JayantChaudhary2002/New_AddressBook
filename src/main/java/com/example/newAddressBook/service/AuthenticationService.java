package com.example.newAddressBook.service;

import com.example.newAddressBook.dto.AuthUserDTO;
import com.example.newAddressBook.dto.LoginDTO;
import com.example.newAddressBook.model.AuthUser;
import com.example.newAddressBook.repository.AuthUserRepository;
import com.example.newAddressBook.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

    public AuthUser register(AuthUserDTO authUserDTO) {
        AuthUser user = AuthUser.builder()
                .email(authUserDTO.getEmail())
                .password(passwordEncoder.encode(authUserDTO.getPassword()))
                .name(authUserDTO.getName())
                .build();
        authUserRepository.save(user);
        return user;
    }

    public Optional<String> login(LoginDTO loginDTO) {
        Optional<AuthUser> user = authUserRepository.findByEmail(loginDTO.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get().getEmail());
            sendTokenByEmail(user.get().getEmail(), token); // Send token via email
            return Optional.of(token);
        }
        return Optional.empty();
    }

    private void sendTokenByEmail(String email, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Your JWT Token");
            helper.setText("Here is your JWT token: " + token, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
