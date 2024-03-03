package com.iablonski.planner.mapper;

import com.iablonski.planner.domain.Stat;
import com.iablonski.planner.dto.StatDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatMapper {

    StatDTO toDTO(Stat stat);
}