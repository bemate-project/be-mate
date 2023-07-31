package com.bemate.domain.application.endpoint;

import com.bemate.domain.application.endpoint.response.ApplicationPetDto;
import com.bemate.domain.application.service.ApplicationQueryService;
import com.bemate.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationQueryEndpoint {

    private final ApplicationQueryService applicationQueryService;
    private final UserQueryService userQueryService;

    @GetMapping("/applications/{id}")
    public ResponseEntity<List<ApplicationPetDto>> findApplications(@PathVariable(value = "id") Long userNo) {
        return ResponseEntity.ok(
                applicationQueryService.getPetJoin(userQueryService.findById(userNo))
        );
    }
}
