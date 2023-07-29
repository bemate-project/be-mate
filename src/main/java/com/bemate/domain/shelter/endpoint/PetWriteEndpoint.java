package com.bemate.domain.shelter.endpoint;


import com.bemate.domain.auth.Principal;
import com.bemate.domain.shelter.endpoint.request.PetWriteRequest;
import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.domain.shelter.service.PetWriteService;
import com.bemate.domain.shelter.service.ShelterUserQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequiredArgsConstructor
public class PetWriteEndpoint {

    private final ShelterUserQueryService shelterUserQueryService;
    private final PetWriteService petWriteService;

    @PostMapping("/shelters/{id}/pet")
    public ResponseEntity<HttpStatus> register(@PathVariable(value = "id") Long shelterNo,
                                                  @RequestPart(value = "petInfo") @Valid PetWriteRequest petWriteRequest,
                                                  @RequestPart(value = "images", required = false) List<MultipartFile> requestImages,
                                                  @AuthenticationPrincipal Principal principal) throws IOException {

        var shelterUser = shelterUserQueryService.getUserJoin(shelterNo);

        if(shelterUser.getUser().getId() != principal.getUserNo()) {
            System.out.printf("Invalid shelterNo - " + shelterNo);
        }

        var pet = petWriteRequest.toPet(shelterUser.getShelter());

        List<PetImageFile> images = requestImages == null
                ? emptyList()
                : requestImages.stream()
                .map(image -> new PetImageFile(pet, image))
                .toList();

        petWriteService.save(pet, images);

        return new ResponseEntity(HttpStatus.OK);
    }
}
