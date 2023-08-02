package com.bemate.domain.application.endpoint.response;

import com.bemate.domain.application.endpoint.response.dto.ApplicationDto;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.user.endpoint.response.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationUserDto {
    private ApplicationDto application;
    private UserDto user;

    public static ApplicationUserDto from(Application application) {
        return ApplicationUserDto.builder()
                .application(ApplicationDto.from(application))
                .user(UserDto.from(application.getUser()))
                .build();
    }
}
