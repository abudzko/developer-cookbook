package com.budzko.app.jpa.repo;

import com.budzko.app.jpa.repo.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<CarEntity, String> {
}
