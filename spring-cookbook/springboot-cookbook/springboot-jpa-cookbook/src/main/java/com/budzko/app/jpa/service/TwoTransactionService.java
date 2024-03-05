package com.budzko.app.jpa.service;

import com.budzko.app.jpa.repo.UsersRepo;
import com.budzko.app.jpa.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwoTransactionService {

    private final UsersRepo usersRepo;
    @Autowired
    private TwoTransactionService transactionService;

    @SneakyThrows
    @Transactional
    public void method1(String id) {
        log.info("Start transaction {}", id);
        log.info("User count: {}", usersRepo.count());
//        transactionService.method2(id);
//        method2(id);//LazyInitializationException
        log.info("Stop transaction {}", id);
    }

    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void method2(String id) {
        log.info("Start transaction {}", id);
        log.info("User count: {}", usersRepo.count());
        List<UserEntity> all = usersRepo.findAll();
        log.info("" + all.stream().findFirst().get().getCars().size());
        log.info("Stop transaction {}", id);
    }
}
