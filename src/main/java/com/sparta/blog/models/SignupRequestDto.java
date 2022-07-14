package com.sparta.blog.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String password2;
    private String email;

    @Override
    public String toString() {
        return "SignupRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}