package com.iablonski.planner.dto;


public record CategoryDTO(String id, String title, Long completedCount, Long uncompletedCount, String userId) {
}