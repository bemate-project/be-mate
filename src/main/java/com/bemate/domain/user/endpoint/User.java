package com.bemate.domain.user.endpoint;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;
    private String userId;
    private String email;
    private String salt;
    private String password;
    private String appleId;
    private String googleId;
}
