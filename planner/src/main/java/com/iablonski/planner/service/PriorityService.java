package com.iablonski.planner.service;

import com.iablonski.planner.dto.PriorityDTO;

import java.util.List;

public interface PriorityService {
    PriorityDTO getPriorityById(String id);
    void createPriority(PriorityDTO priorityDTO);
    void updatePriority(PriorityDTO priorityDTO);
    void deletePriorityById(String id);
    List<PriorityDTO> getPrioritiesByUserId(String userId);
    List<PriorityDTO> findByTitle(String title, String userId);
}