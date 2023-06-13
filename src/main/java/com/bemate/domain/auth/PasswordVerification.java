package com.bemate.domain.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PasswordVerification {
    private String plainPassword;
    private String salt;
    private String hashedPassword;
}
