package com.budzko.app.jpa.repo;

import com.budzko.app.jpa.repo.entity.UserCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepo extends JpaRepository<UserCommentEntity, String> {
}
