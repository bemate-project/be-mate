package com.bemate.domain.user.endpoint;

import com.bemate.domain.user.Role;
import com.bemate.global.util.PasswordUtil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Role role;

    public void hashCredential(String password) {
        var salt = PasswordUtil.genSalt();
        var hashedPassword = PasswordUtil.hashPassword(password, salt);

        this.salt = salt;
        this.password = hashedPassword;
    }
}