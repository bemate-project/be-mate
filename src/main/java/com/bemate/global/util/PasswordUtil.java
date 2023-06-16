package com.bemate.global.util;

import com.bemate.domain.auth.PasswordVerification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    public static String hashPassword(String password, String salt) {
        var bcryptSalt = genSalt();
        return BCrypt.hashpw(password + salt, bcryptSalt);
    }

    public static String genSalt() {
        return BCrypt.gensalt();
    }

    public static boolean matches(PasswordVerification passwordVerification) {
        var salt = passwordVerification.getSalt();
        var plainPassword = passwordVerification.getPlainPassword();
        var hashedPassword = passwordVerification.getHashedPassword();

        var passwordEncoder = ApplicationContextProvider
                .getApplicationContext()
                .getBean("passwordEncoder", PasswordEncoder.class);
        return passwordEncoder.matches(plainPassword + salt, hashedPassword);
    }
}
