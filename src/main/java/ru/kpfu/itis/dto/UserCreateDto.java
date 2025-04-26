package ru.kpfu.itis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCreateDto {

    private String username;

    private String password;

    private String email;

}
