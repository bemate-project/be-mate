package com.bemate.domain.shelter.entity;

import com.bemate.domain.user.entity.BaseEntity;
import com.bemate.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShelterUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_user_no")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User user;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "shelter_no")
    private Shelter shelter;
}
