package budzko.zoo.action.mapper;

import budzko.zoo.action.repo.entity.UserActionEntity;
import budzko.zoo.action.service.dto.UserActionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserActionMapper {
    UserActionEntity convert(UserActionDto userActionDto);

    UserActionDto convert(UserActionEntity userActionEntity);
}
