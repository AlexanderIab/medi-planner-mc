package com.iablonski.planner.service;

import com.iablonski.planner.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface TaskService {

    TaskDTO getTaskById(String id);

    List<TaskDTO> getTasksByUserId(String userId);

    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    void deleteTaskById(String id);

    Page<TaskDTO> findByParams(String title,
                               Boolean completed,
                               String userId,
                               String priorityId,
                               String categoryId,
                               Date dateFrom,
                               Date dateTo,
                               PageRequest pageRequest);
}