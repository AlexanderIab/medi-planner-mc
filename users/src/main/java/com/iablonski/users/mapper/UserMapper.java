package com.iablonski.users.mapper;

import com.iablonski.users.domain.User;
import com.iablonski.users.dto.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    @InheritInverseConfiguration
    UserDTO toDTO(User user);
}