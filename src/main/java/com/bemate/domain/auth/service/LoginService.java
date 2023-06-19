package com.bemate.domain.auth.service;

import com.bemate.domain.auth.endpoint.response.LoginResponse;
import com.bemate.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenIssueService tokenIssueService;

    public LoginResponse getLoginResponse(User user) {
        var tokens = tokenIssueService.getTokensOfUser(user.getId());
        return LoginResponse.from(user, tokens);
    }
}
