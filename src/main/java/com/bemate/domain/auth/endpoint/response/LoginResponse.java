package com.bemate.domain.auth.endpoint.response;

import com.bemate.domain.auth.endpoint.response.dto.TokenDto;
import com.bemate.domain.auth.jwt.Token;
import com.bemate.domain.shelter.endpoint.response.dto.ShelterDto;
import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.user.endpoint.response.dto.UserDto;
import com.bemate.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoginResponse {
    private UserDto user;
    private List<ShelterDto> shelters;
    private TokenDto tokens;

    public static LoginResponse from(User user, Token token) {
        return LoginResponse.builder()
                .user(UserDto.from(user))
                .tokens(TokenDto.from(token))
                .build();
    }

    public static LoginResponse from(User user, List<Shelter> shelters, Token token) {
        return LoginResponse.builder()
                .user(UserDto.from(user))
                .shelters(shelters.stream().map(shelter -> ShelterDto.from(shelter)).toList())
                .tokens(TokenDto.from(token))
                .build();
    }
}
