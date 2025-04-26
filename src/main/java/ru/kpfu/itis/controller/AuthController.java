package ru.kpfu.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.kpfu.itis.dto.UserCreateDto;
import ru.kpfu.itis.dto.UserLoginParam;
import ru.kpfu.itis.dto.UserRegistrationParam;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.CustomUserDetailsService;
import ru.kpfu.itis.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String registration() {
        return "sign-up";
    }

    @GetMapping("/verification")
    public String verification(@RequestParam(value = "code", required = false) String code) {
        try {
            UUID verificationCode = UUID.fromString(code);
            System.out.println(verificationCode);
            userService.verifyUser(verificationCode.toString());
            return "redirect:/auth/login";
        } catch (IllegalArgumentException e) {
            return "redirect:/auth/login?error=invalid_code";
        } catch (RuntimeException e) {
            return "redirect:/auth/login?error=verification_failed";
        }
    }

    @PostMapping("/sign-up")
    public String registrationPos(@ModelAttribute UserCreateDto createUserDto) {
        userService.create(createUserDto, "http://localhost:8080");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


}

