package ru.kpfu.itis.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.config.MailConfig;
import ru.kpfu.itis.dto.UserCreateDto;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.entity.Role;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;


    public UserDto create(UserCreateDto dto, String baseUrl) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        String verification = UUID.randomUUID().toString();
        user.setVerificationCode(verification);
        user.setRoles(Collections.singleton(Role
                .builder()
                .name("USER")
                .build()));

        sendVerificationEmail(dto, baseUrl, verification);

        return UserDto.fromUser(userRepository.save(user));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }
    public UserDto findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new RuntimeException("user not found");
        }
        return UserDto.fromUser(user.get());
    }

    private void sendVerificationEmail(UserCreateDto dto, String baseUrl, String verificationCode) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String content = mailConfig.getContent();

        try {
            mimeMessageHelper.setFrom(mailConfig.getFrom(), mailConfig.getSender());
            mimeMessageHelper.setTo(dto.getEmail());
            mimeMessageHelper.setSubject(mailConfig.getSubject());

            content = content.replace("{name}", dto.getUsername());
            content = content.replace("{url}", baseUrl + "/auth/verification?code=" + verificationCode);

            mimeMessageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyUser(String code) {
        User user = userRepository.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid verification code"));
        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepository.save(user);
        return true;
    }
}