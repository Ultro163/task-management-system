package com.example.dto.mappers;

import com.example.dto.user.NewUserDto;
import com.example.dto.user.UserDto;
import com.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(NewUserDto newUserDto);

    UserDto toUserDto(User user);
}