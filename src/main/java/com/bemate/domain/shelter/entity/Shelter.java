package com.bemate.domain.shelter.entity;

import com.bemate.domain.review.entity.Review;
import com.bemate.domain.user.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
