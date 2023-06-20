package com.bemate.domain.shelter.entity;

import com.bemate.domain.user.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Builder
@Getter
public class Shelter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_no")
    private Long id;
    private String shelterName;
    private String email;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;

    @OneToMany(mappedBy = "shelter")
    private List<ShelterUser> shelterUsers;
}
