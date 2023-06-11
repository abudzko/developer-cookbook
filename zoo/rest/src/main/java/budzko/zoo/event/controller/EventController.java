package budzko.zoo.event.controller;

import budzko.zoo.event.service.EventService;
import budzko.zoo.event.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/generateEvent")
    EventDto generateEvent() {
        return eventService.generateEvent();
    }

    @GetMapping("/event")
    EventDto event(@RequestParam String id) {
        return eventService.get(id);
    }

    @GetMapping("/events")
    List<EventDto> events(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return eventService.events(PageRequest.of(page, pageSize));
    }
}
