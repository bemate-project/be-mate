package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.endpoint.request.PetQueryRequest;
import com.bemate.domain.shelter.endpoint.response.dto.PetDto;
import com.bemate.domain.shelter.endpoint.response.dto.PetShelterDto;
import com.bemate.domain.shelter.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetQueryService {

    private final PetRepository petRepository;

    public Page<PetShelterDto> findByCondition(PetQueryRequest request, Pageable pageable) {
        return petRepository.findByCondition(request, pageable);
    }

    public Page<PetDto> findByShelterNo(Long shelterNo, Pageable pageable) {
        var page = petRepository.findByShelterNo(shelterNo, pageable);
        return page.map(PetDto::new);
    }
}
