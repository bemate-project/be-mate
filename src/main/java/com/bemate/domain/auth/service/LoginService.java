package com.bemate.domain.auth.service;

import com.bemate.domain.auth.endpoint.response.LoginResponse;
import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.service.ShelterUserQueryService;
import com.bemate.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List<ShelterUser> byUserNo = shelterUserQueryService.findByUser(user);

        var shelters = byUserNo
                .stream()
                .map(ShelterUser::getShelter)
                .toList();

        return LoginResponse.from(user, shelters, tokens);
    }
}
