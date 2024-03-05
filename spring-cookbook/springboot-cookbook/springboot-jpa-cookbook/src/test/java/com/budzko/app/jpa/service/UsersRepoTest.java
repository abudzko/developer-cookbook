package com.budzko.app.jpa.service;

import com.budzko.app.jpa.repo.CarRepo;
import com.budzko.app.jpa.repo.PasswordRepo;
import com.budzko.app.jpa.repo.UserCommentRepo;
import com.budzko.app.jpa.repo.UsersRepo;
import com.budzko.app.jpa.repo.entity.UserCommentEntity;
import com.budzko.app.jpa.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;
import java.util.Set;

@SpringBootTest
@TestPropertySource(
        properties = {
                //Separated db should be created for this test to not conflict with another tests
                "spring.datasource.db-name=UserRepoTest"
        }
)
@Slf4j
@RequiredArgsConstructor
public class UsersRepoTest {

    @Autowired
    private final UsersRepo usersRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private PasswordRepo passwordRepo;
    @Autowired
    private UserCommentRepo userCommentRepo;

    @BeforeEach
    public void beforeEach() {
        usersRepo.deleteAll();
    }

    @Test
    public void testSaveUsers() {
        Set<UserEntity> userEntities = EntityUtils.createUserEntities(
                3,
                Map.of(
                        EntityUtils.PASSWORD_COUNT, 1,
                        EntityUtils.CAR_COUNT, 2
                )
        );

        for (UserEntity userEntity : userEntities) {
            usersRepo.save(userEntity);
        }

        usersRepo.deleteById(userEntities.stream().findFirst().get().getId());
        log.info(carRepo.findAll().toString());
        log.info(passwordRepo.findAll().toString());
    }

    @Test
    public void testSaveUserComments() {
        Set<UserEntity> userEntities = EntityUtils.createUserEntities(
                1,
                Map.of(
                        EntityUtils.PASSWORD_COUNT, 1,
                        EntityUtils.CAR_COUNT, 1
                )
        );

        UserEntity userEntity = userEntities.stream().findFirst().orElseThrow();
        usersRepo.save(userEntity);
        Set<UserCommentEntity> userCommentEntities = EntityUtils.createUserCommentEntities(2, userEntity);

        userCommentEntities.forEach(userCommentEntity -> userCommentRepo.save(userCommentEntity));
        userCommentRepo.deleteAll();
    }
}
