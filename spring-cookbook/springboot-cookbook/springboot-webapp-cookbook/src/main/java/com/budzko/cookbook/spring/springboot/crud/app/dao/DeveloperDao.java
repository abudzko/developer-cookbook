package com.budzko.cookbook.spring.springboot.crud.app.dao;

import com.budzko.cookbook.spring.springboot.crud.app.dao.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperDao extends JpaRepository<Developer, Long> {
}
