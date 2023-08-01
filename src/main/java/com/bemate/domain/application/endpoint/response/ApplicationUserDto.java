package com.bemate.domain.application.endpoint.response;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.HomeConditions;
import com.bemate.domain.application.entity.Application;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationUserDto {
    private Long applicationNo;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;
    private HomeConditions homeConditions;
    private ApplicationStatus applicationStatus;
    private Long userNo;
    private String email;
    private String nickname;

    public static ApplicationUserDto from(Application application) {
        var user = application.getUser();
        return ApplicationUserDto.builder()
                .applicationNo(application.getId())
                .zipCode(application.getZipCode())
                .streetAddress(application.getStreetAddress())
                .detailAddress(application.getDetailAddress())
                .homeConditions(application.getHomeConditions())
                .applicationStatus(application.getApplicationStatus())
                .userNo(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
