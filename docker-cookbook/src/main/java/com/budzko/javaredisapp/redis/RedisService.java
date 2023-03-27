package com.budzko.javaredisapp.redis;

import com.budzko.javaredisapp.redis.repo.CounterRepo;
import com.budzko.javaredisapp.redis.repo.entity.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final CounterRepo counterRepo;

    public int increment() {
        Optional<Counter> counterOptional = counterRepo.findById("counter");

        Counter counter;
        if (counterOptional.isEmpty()) {
            counter = new Counter();
            counter.setId("counter");
            counter.setValue(0);
        } else {
            counter = counterOptional.get();
            if (counter.getValue() == null) {
                counter.setValue(0);
            }
        }
        Integer result = counter.getValue();
        counter.setValue(counter.getValue() + 1);
        counterRepo.save(counter);
        return result;
    }

    public long size() {
        return counterRepo.count();
    }
}
