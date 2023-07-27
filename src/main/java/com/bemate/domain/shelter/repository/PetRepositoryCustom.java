package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.endpoint.request.PetQueryRequest;
import com.bemate.domain.shelter.endpoint.response.dto.PetShelterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepositoryCustom {
    Page<PetShelterDto> findByCondition(PetQueryRequest request, Pageable pageable);
}
