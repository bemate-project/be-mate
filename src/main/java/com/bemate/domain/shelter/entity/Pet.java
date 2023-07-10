package com.bemate.domain.shelter.entity;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import com.bemate.domain.user.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_key")
    private String petKey;
    private String species;
    private String kind;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String characteristics;
    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;
    private String imageFiles;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;
}
