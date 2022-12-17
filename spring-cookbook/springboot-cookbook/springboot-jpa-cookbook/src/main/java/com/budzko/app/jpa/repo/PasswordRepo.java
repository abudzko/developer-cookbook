package com.budzko.app.jpa.repo;

import com.budzko.app.jpa.repo.entity.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepo extends JpaRepository<PasswordEntity, String> {
}
