package com.iablonski.planner.dto;

import com.iablonski.planner.domain.Category;
import com.iablonski.planner.domain.Priority;

import java.util.Date;

public record TaskDTO (
        String id,
        String title,
        Boolean completed,
        Date taskDate,
        Priority priority,
        Category category,
        String userId) {
}