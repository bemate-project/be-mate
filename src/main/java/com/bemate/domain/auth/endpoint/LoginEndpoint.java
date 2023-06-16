package com.bemate.domain.auth.endpoint;

import com.bemate.domain.auth.PasswordVerification;
import com.bemate.domain.auth.endpoint.request.LoginRequest;
import com.bemate.domain.user.service.UserQueryService;
import com.bemate.global.exception.PasswordMismatchException;
import com.bemate.global.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginEndpoint {

    private final UserQueryService userQueryService;

    @PostMapping("/auths/login")
    public HttpStatus login(@RequestBody LoginRequest loginRequest) {
        var user = userQueryService.findByEmail(loginRequest.getEmail());

        var passwordVerification = PasswordVerification.builder()
                .plainPassword(loginRequest.getPassword())
                .salt(user.getSalt())
                .hashedPassword(user.getPassword())
                .build();

        if(!PasswordUtil.matches(passwordVerification)) {
            throw new PasswordMismatchException();
        }

        return HttpStatus.OK;
    }
}
