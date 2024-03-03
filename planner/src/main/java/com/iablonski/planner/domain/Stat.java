package com.iablonski.planner.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Stat {
    @Id
    private String id;
    private Long completedTotal;
    private Long uncompletedTotal;
    private String userId;
}