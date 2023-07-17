package com.bemate.domain.shelter.entity;

import com.bemate.domain.user.entity.BaseEntity;
import com.bemate.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShelterUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_user_no")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User user;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "shelter_no")
    private Shelter shelter;
}
