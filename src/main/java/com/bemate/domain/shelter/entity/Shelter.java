package com.bemate.domain.shelter.entity;

import com.bemate.domain.user.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}
