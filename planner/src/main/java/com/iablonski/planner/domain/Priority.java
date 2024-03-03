package com.iablonski.planner.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Priority {
    @Id
    private String id;
    private String title;
    private String color;
    private String userId;
}