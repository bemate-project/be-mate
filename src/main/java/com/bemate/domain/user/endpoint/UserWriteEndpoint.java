package com.bemate.domain.user.endpoint;

import com.bemate.domain.user.endpoint.request.UserRegisterRequest;
import com.bemate.domain.user.service.UserWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserWriteEndpoint {

    private final UserWriteService userWriteService;

    @PostMapping("/users")
    public ResponseEntity register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userWriteService.register(userRegisterRequest.toUser());

        return new ResponseEntity(HttpStatus.OK);
    }
}
