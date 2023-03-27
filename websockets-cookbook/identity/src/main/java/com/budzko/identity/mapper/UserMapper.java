package com.budzko.identity.mapper;


import com.budzko.identity.model.http.UserRegistrationRequest;
import com.budzko.identity.repo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserEntity convert(UserRegistrationRequest userRegistrationRequest);
}
