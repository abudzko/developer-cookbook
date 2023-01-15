package com.budzko.identity.registration.service;

import com.budzko.identity.mapper.UserMapper;
import com.budzko.identity.registration.model.UserCredentials;
import com.budzko.identity.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public void registerUser(UserCredentials userCredentials) {
//        log.info("User was registered: {}", userCredentials.getLogin());
//        UserEntity userEntity = userMapper.convert(userCredentials);
//        userRepo.save(userEntity);
    }

    public boolean existByLogin(String login) {
        return userRepo.existsByLogin(login);
    }

}
