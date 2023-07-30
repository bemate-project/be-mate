package com.bemate.domain.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationQueryEndpoint {

    @GetMapping("/applications/{id}")
    public ResponseEntity findApplications(@PathVariable(value = "id") Long userNo) {
        return null;
    }
}
