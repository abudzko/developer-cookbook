package budzko.zoo.event.mapper;

import budzko.zoo.event.postgres.repo.reader.entity.ReadEventEntity;
import budzko.zoo.event.postgres.repo.writer.entity.WriteEventEntity;
import budzko.zoo.event.service.dto.EventDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    WriteEventEntity convert(EventDto eventDto);

    EventDto convert(ReadEventEntity eventEntity);

    List<EventDto> convert(List<ReadEventEntity> eventEntities);
}
