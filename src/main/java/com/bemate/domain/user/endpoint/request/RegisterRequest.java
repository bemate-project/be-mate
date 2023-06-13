package com.bemate.domain.user.endpoint.request;

import com.bemate.domain.user.Role;
import com.bemate.domain.user.endpoint.User;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class RegisterRequest {
    @NonNull
    private String email;
    @NonNull
    private String nickname;
    @NonNull
    private String password;
    @NonNull
    private Role role;
    @Nullable
    private String shelterName;

    public User toUser() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .role(role)
                .build();
    }
}
