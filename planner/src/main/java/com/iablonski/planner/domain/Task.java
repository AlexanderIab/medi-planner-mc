package com.iablonski.planner.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class Task {
    @Id
    private String id;
    private String title;
    private Boolean completed;
    private Date taskDate;
    @DBRef
    private Priority priority;
    @DBRef
    private Category category;
    private String userId;
}