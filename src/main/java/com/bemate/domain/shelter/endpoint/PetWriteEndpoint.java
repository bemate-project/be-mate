package com.bemate.domain.shelter.endpoint;


import com.bemate.domain.auth.Principal;
import com.bemate.domain.shelter.endpoint.request.PetWriteRequest;
import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.domain.shelter.service.PetQueryService;
import com.bemate.domain.shelter.service.PetWriteService;
import com.bemate.domain.shelter.service.ShelterUserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pet")
public class PetWriteEndpoint {

    private final ShelterUserQueryService shelterUserQueryService;
    private final PetWriteService petWriteService;
    private final PetQueryService petQueryService;

    @PostMapping("/shelters/{id}/pet")
    @Operation(summary = "Register Pet")
    public ResponseEntity<HttpStatus> register(@PathVariable(value = "id") Long shelterNo,
                                                  @RequestPart(value = "petInfo") @Valid PetWriteRequest petWriteRequest,
                                                  @RequestPart(value = "images", required = false) List<MultipartFile> requestImages,
                                                  @AuthenticationPrincipal Principal principal) throws IOException {

        var shelterUser = shelterUserQueryService.findByShelter(shelterNo);
        var pet = petWriteRequest.toPet(shelterUser.getShelter());

        List<PetImageFile> images = requestImages == null
                ? emptyList()
                : requestImages.stream()
                .map(image -> new PetImageFile(pet, image))
                .toList();

        petWriteService.register(pet, images);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/shelters/{id}/pet/{key}")
    public ResponseEntity<HttpStatus> update(@PathVariable(value = "id") Long shelterNo,
                                             @PathVariable(value = "key") String petKey,
                                             @RequestPart(value = "petInfo") @Valid PetWriteRequest petWriteRequest,
                                             @RequestPart(value = "images", required = false) List<MultipartFile> requestImages) {

        var pet = petQueryService.findById(petKey);

        pet.updatePetInfo(petWriteRequest);

        List<PetImageFile> images = requestImages == null
                ? emptyList()
                : requestImages.stream()
                .map(image -> new PetImageFile(pet, image))
                .toList();

        petWriteService.update(pet, images);

        return new ResponseEntity(HttpStatus.OK);
    }
}
