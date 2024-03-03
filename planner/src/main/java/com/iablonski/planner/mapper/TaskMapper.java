package com.iablonski.planner.mapper;

import com.iablonski.planner.domain.Task;
import com.iablonski.planner.dto.TaskDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(TaskDTO taskDTO);
    @InheritInverseConfiguration
    TaskDTO toDTO(Task task);
}