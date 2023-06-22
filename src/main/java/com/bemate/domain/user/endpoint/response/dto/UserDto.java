package com.bemate.domain.user.endpoint.response.dto;

import com.bemate.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private Long userNo;
    private String email;
    private String nickname;

    public static UserDto from(User user) {
        return UserDto.builder()
                .userNo(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
