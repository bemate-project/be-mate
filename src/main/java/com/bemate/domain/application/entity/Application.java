package com.bemate.domain.application.entity;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.HomeConditions;
import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_no")
    private Long id;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;
    @Enumerated(EnumType.STRING)
    private HomeConditions homeConditions;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_key")
    private Pet pet;

    public void updateApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
