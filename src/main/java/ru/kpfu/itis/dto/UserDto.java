package ru.kpfu.itis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.entity.User;

@Getter
@Setter
@Builder
public class UserDto {

    private String username;

    private String email;
    private boolean isEnabled;


    public static UserDto fromUser(User user) {
        return UserDto
                .builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .isEnabled(user.isEnabled())
                .build();
    }
}
