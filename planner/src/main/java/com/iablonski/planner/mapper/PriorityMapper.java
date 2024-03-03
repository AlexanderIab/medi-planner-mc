package com.iablonski.planner.mapper;

import com.iablonski.planner.domain.Priority;
import com.iablonski.planner.dto.PriorityDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityMapper {

    Priority toPriority(PriorityDTO priorityDTO);
    @InheritInverseConfiguration
    PriorityDTO toDTO(Priority priority);
}
