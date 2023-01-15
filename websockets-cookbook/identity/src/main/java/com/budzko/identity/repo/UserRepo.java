package com.budzko.identity.repo;

import com.budzko.identity.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

    boolean existsByLogin(String login);

}
