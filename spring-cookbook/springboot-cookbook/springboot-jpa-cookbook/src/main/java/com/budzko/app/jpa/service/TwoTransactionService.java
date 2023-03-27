package com.budzko.app.jpa.service;

import com.budzko.app.jpa.repo.UserRepo;
import com.budzko.app.jpa.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwoTransactionService {

    private final UserRepo userRepo;
    @Autowired
    private TwoTransactionService transactionService;

    @SneakyThrows
    public void method1(String id) {
        log.info("Start transaction {}", id);
        log.info("User count: {}", userRepo.count());
//        transactionService.method2(id);
        method2(id);
        log.info("Stop transaction {}", id);
    }

    @SneakyThrows
    @Transactional
    public void method2(String id) {
        log.info("Start transaction {}", id);
        log.info("User count: {}", userRepo.count());
        List<UserEntity> all = userRepo.findAll();
        log.info("" + all.stream().findFirst().get().getCars().size());
        log.info("Stop transaction {}", id);
    }
}
