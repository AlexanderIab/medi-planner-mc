package com.iablonski.planner.service.serviceImpl;

import com.iablonski.planner.domain.Category;
import com.iablonski.planner.domain.Priority;
import com.iablonski.planner.domain.Task;
import com.iablonski.planner.dto.TaskDTO;
import com.iablonski.planner.mapper.TaskMapper;
import com.iablonski.planner.repository.CategoryRepo;
import com.iablonski.planner.repository.PriorityRepo;
import com.iablonski.planner.repository.TaskRepo;
import com.iablonski.planner.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    private final CategoryRepo categoryRepo;
    private final PriorityRepo priorityRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, TaskMapper taskMapper, CategoryRepo categoryRepo, PriorityRepo priorityRepo) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
        this.categoryRepo = categoryRepo;
        this.priorityRepo = priorityRepo;
    }

    @Override
    public TaskDTO getTaskById(String id) {
        Task task = taskRepo.findById(id).orElseThrow();
        return taskMapper.toDTO(task);
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
//        if (taskRepo.existsById(taskDTO.id())) {
//            throw new RuntimeException("Already Exists");
//        }
//        Task task = taskMapper.toTask(taskDTO);
//        task.setId(null);
        Priority priority = priorityRepo.findById(taskDTO.priority().getId()).orElseThrow();
        Category category = categoryRepo.findById(taskDTO.category().getId()).orElseThrow();
        Task task = new Task();
        task.setTitle(taskDTO.title());
        task.setCompleted(taskDTO.completed());
        task.setTaskDate(taskDTO.taskDate());
        task.setPriority(priority);
        task.setCategory(category);
        task.setUserId(taskDTO.userId());
        taskRepo.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        if (!taskRepo.existsById(taskDTO.id())) {
            throw new RuntimeException("Not Exists");
        }
        Task task = taskMapper.toTask(taskDTO);
        taskRepo.save(task);
    }

    @Override
    public void deleteTaskById(String id) {
        if (!taskRepo.existsById(id)) {
            throw new RuntimeException("Not Exists");
        }
        taskRepo.deleteById(id);
    }

    @Override
    public List<TaskDTO> getTasksByUserId(String userId) {
        return taskRepo.findByUserIdOrderByTitleAsc(userId).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TaskDTO> findByParams(String title, Boolean completed,
                                      String userId, String priorityId,
                                      String categoryId, Date dateFrom,
                                      Date dateTo, PageRequest pageRequest) {
        Page<Task> page = taskRepo.findTaskByParams(
                title, completed, userId, priorityId,
                categoryId, dateFrom, dateTo, pageRequest);
        return page.map(taskMapper::toDTO);
    }
}