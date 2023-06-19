package com.bemate.domain.user.endpoint.request;

import com.bemate.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;

    public User toUser() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
