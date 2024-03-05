package com.budzko.app.jpa.service;

import com.budzko.app.jpa.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final UsersRepo usersRepo;

    @SneakyThrows
    @Transactional
    public void execute(String id, Runnable runnable) {
        log.info("Start transaction {}", id);
        log.info("User count: {}", usersRepo.count());
        runnable.run();
        log.info("Stop transaction {}", id);
    }
}
