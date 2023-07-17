package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.endpoint.request.PetQueryRequest;
import com.bemate.domain.shelter.endpoint.response.dto.PetShelterDto;
import com.bemate.domain.shelter.endpoint.response.dto.QPetShelterDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.bemate.domain.shelter.entity.QPet.pet;
import static com.bemate.domain.shelter.entity.QShelter.shelter;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PetShelterDto> searchPage(PetQueryRequest request, Pageable pageable) {
        var results = queryFactory
                .select(new QPetShelterDto(
                        pet.id.as("petKey"),
                        pet.species,
                        pet.kind,
                        pet.age,
                        pet.gender,
                        pet.characteristics,
                        pet.healthStatus,
                        pet.imageFolder,
                        pet.imageFiles,
                        pet.adoptionStatus,
                        shelter.id.as("shelterNo"),
                        shelter.shelterName,
                        shelter.email,
                        shelter.zipCode,
                        shelter.streetAddress,
                        shelter.detailAddress
                ))
                .from(pet)
                .leftJoin(pet.shelter, shelter)
                .where(
                        petSpeciesEq(request.getSpecies()),
                        petKindEq(request.getKind()),
                        petAdoptionStatusEq(request.getAdoptionStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var size = results.size();

        return new PageImpl<>(results, pageable, size);
    }

    private BooleanExpression petSpeciesEq(String species) {
        return hasText(species) ? pet.species.eq(species) : null;
    }

    private BooleanExpression petKindEq(String kind) {
        return hasText(kind) ? pet.kind.eq(kind) : null;
    }

    private BooleanExpression petAdoptionStatusEq(AdoptionStatus adoptionStatus) {
        return adoptionStatus != null ? pet.adoptionStatus.eq(adoptionStatus) : null;
    }
}
