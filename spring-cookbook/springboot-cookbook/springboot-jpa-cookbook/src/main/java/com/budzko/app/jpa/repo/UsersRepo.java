package com.budzko.app.jpa.repo;

import com.budzko.app.jpa.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<UserEntity, String> {
}
