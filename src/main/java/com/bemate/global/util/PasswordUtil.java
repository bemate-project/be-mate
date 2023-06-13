package com.bemate.global.util;

import com.bemate.domain.auth.PasswordVerification;
import com.bemate.global.config.SecurityConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        var ac = new AnnotationConfigApplicationContext(SecurityConfig.class);
        var passwordEncoder = ac.getBean("passwordEncoder", PasswordEncoder.class);

        var salt = passwordVerification.getSalt();
        var plainPassword = passwordVerification.getPlainPassword();
        var hashedPassword = passwordVerification.getHashedPassword();

        return passwordEncoder.matches(plainPassword + salt, hashedPassword);
    }

}
