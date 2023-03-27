package com.budzko.app.jpa.service;

import com.budzko.app.jpa.repo.UserRepo;
import com.budzko.app.jpa.repo.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@SpringBootTest
/**
 * If com.budzko.app.jpa.service.TwoTransactionService#method1(java.lang.String) is not @Transactional and
 * within it @Transactional method2 is collect, transaction will not we started as method2 called on NOT proxy instance
 */
public class TwoTransactionServiceTest {

    @Autowired
    private TwoTransactionService transactionService;
    @Autowired
    private UserRepo userRepo;

    @Test
    void test() {
        saveUsers();
        transactionService.method1(UUID.randomUUID().toString());
    }

    private void saveUsers() {
        Set<UserEntity> userEntities = EntityUtils.createUserEntities(
                3,
                Map.of(
                        EntityUtils.PASSWORD_COUNT, 1,
                        EntityUtils.CAR_COUNT, 2
                )
        );

        for (UserEntity userEntity : userEntities) {
            userRepo.save(userEntity);
        }
    }
}
