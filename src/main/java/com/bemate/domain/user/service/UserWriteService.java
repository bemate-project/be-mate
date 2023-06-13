package com.bemate.domain.user.service;

import com.bemate.domain.user.endpoint.User;
import com.bemate.domain.user.repository.UserRepository;
import com.bemate.global.exception.UserAlreadyExistsException;
import com.bemate.global.exception.handler.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserWriteService {

    private final UserQueryService userQueryService;
    private final UserRepository userRepository;

    public void register(User user) {
        if(isAlreadyExists(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        registerInternally(user);
    }

    private boolean isAlreadyExists(String email) {
        try {
            userQueryService.findByEmail(email);
        } catch (NotFoundException e) {
            return false;
        }
        return true;
    }

    private User registerInternally(User user) {
        setSaltAndEncodedPasswordToUser(user);

        return userRepository.save(user);
    }

    private void setSaltAndEncodedPasswordToUser(User user) {
        var originPassword = user.getPassword();
        user.hashCredential(originPassword);
    }
}
