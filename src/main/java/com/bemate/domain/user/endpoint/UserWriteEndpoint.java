package com.bemate.domain.user.endpoint;

import com.bemate.domain.user.endpoint.request.RegisterRequest;
import com.bemate.domain.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserWriteEndpoint {

    private final UserWriteService userWriteService;

    @PostMapping("/users")
    public HttpStatus register(@RequestBody RegisterRequest registerRequest) {
        var user = registerRequest.toUser();

        userWriteService.register(user);

        return HttpStatus.OK;
    }
}
