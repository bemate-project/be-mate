package com.bemate.domain.user.entity;

import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.global.util.PasswordUtil;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;
    private String email;
    private String nickname;
    private String salt;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShelterUser> shelterUsers;

    public void hashCredential(String password) {
        var salt = PasswordUtil.genSalt();
        var hashedPassword = PasswordUtil.hashPassword(password, salt);

        this.salt = salt;
        this.password = hashedPassword;
    }
}