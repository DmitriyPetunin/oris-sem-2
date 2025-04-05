package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.kpfu.itis.dto.UserLoginParam;
import ru.kpfu.itis.dto.UserRegistrationParam;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.CustomUserDetailsService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, CustomUserDetailsService customUserDetailsService
    ) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
    }
    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserRegistrationParam userRegistrationParam) {

        User userEntity = new User();
        userEntity.setUsername(userRegistrationParam.getUsername());
        userEntity.setPassword(userRegistrationParam.getPassword());

        userRepository.save(userEntity);
        return "login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginParam userLoginParam) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userLoginParam.getUsername());

            if (passwordEncoder.matches(userLoginParam.getPassword(), userDetails.getPassword())) {
                return "index";
            } else {
                return "login";
            }
        } catch (UsernameNotFoundException e) {
            return "login";
        }
    }
}
