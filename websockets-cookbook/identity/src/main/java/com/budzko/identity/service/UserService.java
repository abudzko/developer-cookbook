package com.budzko.identity.service;

import com.budzko.identity.mapper.UserMapper;
import com.budzko.identity.model.UserSignUpRequest;
import com.budzko.identity.repo.UserRepo;
import com.budzko.identity.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    //    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findById(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return User.builder()
                .username(userEntity.getLogin())
                .password(userEntity.getPasswordHash())
                .build();
    }

    public void registerUser(UserSignUpRequest userSignUpRequest) {
        UserEntity userEntity = userMapper.convert(userSignUpRequest);
        userEntity.setLogin(userSignUpRequest.getUserLogin());
//        userEntity.setPasswordHash(passwordEncoder.encode(userSignUpRequest.getUserPassword()));
        userRepo.save(userEntity);
        log.info("User was registered: {}", userSignUpRequest.getUserLogin());
    }

    public boolean existByLogin(String login) {
        return userRepo.existsByLogin(login);
    }

}
