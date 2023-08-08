package com.bemate.domain.application.endpoint;

import com.bemate.domain.application.endpoint.request.ApplicationWriteRequest;
import com.bemate.domain.application.service.ApplicationWriteService;
import com.bemate.domain.auth.Principal;
import com.bemate.domain.shelter.service.PetQueryService;
import com.bemate.domain.user.service.UserQueryService;
import com.bemate.global.exception.UserMismatchException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationWriteEndpoint {

    private final PetQueryService petQueryService;
    private final UserQueryService userQueryService;
    private final ApplicationWriteService applicationWriteService;

    @PostMapping("/applications/{id}")
    public ResponseEntity<HttpStatus> write(@PathVariable(value = "id") Long userNo,
                                             @RequestBody @Valid ApplicationWriteRequest applicationWriteRequest,
                                             @AuthenticationPrincipal Principal principal) {

        if(userNo != principal.getUserNo()) {
            throw new UserMismatchException(userNo);
        }

        var user = userQueryService.findById(userNo);
        var pet = petQueryService.findById(applicationWriteRequest.getPetKey());

        applicationWriteService.write(applicationWriteRequest.toApplication(user, pet));

        return new ResponseEntity(HttpStatus.OK);
    }
}
