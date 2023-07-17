package com.bemate.domain.auth.service;

import com.bemate.domain.auth.endpoint.response.LoginResponse;
import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.service.ShelterUserQueryService;
import com.bemate.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenIssueService tokenIssueService;
    private final ShelterUserQueryService shelterUserQueryService;

    public LoginResponse getLoginResponse(User user) {
        var tokens = tokenIssueService.getTokensOfUser(user.getId());

        if(user.getShelterUsers() == null) {
            return LoginResponse.from(user, tokens);
        }

        var shelters = shelterUserQueryService.getShelterJoin(user.getId())
                .stream()
                .map(ShelterUser::getShelter)
                .toList();

        return LoginResponse.from(user, shelters, tokens);
    }
}
