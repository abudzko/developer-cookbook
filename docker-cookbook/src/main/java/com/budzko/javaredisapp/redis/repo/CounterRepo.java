package com.budzko.javaredisapp.redis.repo;

import com.budzko.javaredisapp.redis.repo.entity.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepo extends CrudRepository<Counter, String> {
}
