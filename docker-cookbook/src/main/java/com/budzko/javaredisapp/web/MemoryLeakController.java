package com.budzko.javaredisapp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class MemoryLeakController {
    private final List<byte[]> storage = new ArrayList<>();

    @GetMapping("/allocate")
    public void allocate(int mb) {
        storage.add(new byte[mb * 1024 * 1024]);
        log.info("Allocated {} mb", mb);
    }
}
