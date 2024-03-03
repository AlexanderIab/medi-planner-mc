package com.iablonski.planner.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Category {
    @Id
    private String id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;
    private String userId;
}