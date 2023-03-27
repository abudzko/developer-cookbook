package com.budzko.identity.repo;

import com.budzko.identity.repo.entity.TokenConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenConfigRepo extends JpaRepository<TokenConfigEntity, String> {

    byte[] getTokenPublicKeyById(String login);
}
