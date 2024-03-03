package com.iablonski.planner.dto;

public record StatDTO (String id, Long completedTotal, Long uncompletedTotal, String userId) {
}