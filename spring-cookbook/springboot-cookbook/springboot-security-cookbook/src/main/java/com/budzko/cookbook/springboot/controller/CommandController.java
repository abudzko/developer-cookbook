package com.budzko.cookbook.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/command")
public class CommandController {
    private static final AtomicLong startedCommand = new AtomicLong();

    @PostMapping("/start")
    public String startCommand() {
        startedCommand.incrementAndGet();
        return "Start OK";
    }

    @PostMapping("/stop")
    public String stopStopCommand() {
        startedCommand.decrementAndGet();
        return "Stop OK";
    }

    @GetMapping("/info")
    public String commandInfo() {
        return "Started %s".formatted(startedCommand);
    }

    @GetMapping("/stats")
    public String stats() {
        return "Stats";
    }

    @GetMapping("/noop")
    public String commandNoop() {
        return "Noop";
    }
}
