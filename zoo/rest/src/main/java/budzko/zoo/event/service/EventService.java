package budzko.zoo.event.service;

import budzko.zoo.event.kafka.producer.EventProducer;
import budzko.zoo.event.mapper.EventMapper;
import budzko.zoo.event.postgres.repo.reader.EventReaderRepo;
import budzko.zoo.event.postgres.repo.reader.entity.ReadEventEntity;
import budzko.zoo.event.postgres.repo.writer.EventWriterRepo;
import budzko.zoo.event.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventProducer eventProducer;
    private final EventReaderRepo eventReaderRepo;
    private final EventWriterRepo eventWriterRepo;
    private final EventMapper eventMapper;

    public EventDto generateEvent() {
        EventDto eventDto = createEvent();
        eventProducer.sendEvent(eventDto.toString());
        eventWriterRepo.save(eventMapper.convert(eventDto));
        return eventDto;
    }

    public EventDto get(String eventId) {
        return eventMapper.convert(eventReaderRepo.findById(eventId).orElse(new ReadEventEntity()));
    }

    private EventDto createEvent() {
        EventDto eventDto = new EventDto();
        eventDto.setId(UUID.randomUUID().toString());
        eventDto.setName("EVENT");
        eventDto.setTimestamp(System.currentTimeMillis());
        return eventDto;
    }

    public List<EventDto> events(PageRequest pageRequest) {
        return eventReaderRepo.findAll(pageRequest).getContent().stream()
                .map(eventMapper::convert)
                .toList();
    }
}
