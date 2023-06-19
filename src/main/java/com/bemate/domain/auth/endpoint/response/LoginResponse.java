package com.bemate.domain.auth.endpoint.response;

import com.bemate.domain.auth.jwt.Token;
import com.bemate.domain.auth.endpoint.response.dto.TokenDto;
import com.bemate.domain.auth.endpoint.response.dto.UserDto;
import com.bemate.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private UserDto user;
    private TokenDto tokens;

    public static LoginResponse from(User user, Token token) {
        return LoginResponse.builder()
                .user(UserDto.from(user))
                .tokens(TokenDto.from(token))
                .build();
    }
}
