package com.iablonski.planner.repository;

import com.iablonski.planner.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface TaskRepo extends MongoRepository<Task, String> {

    List<Task> findByUserIdOrderByTitleAsc(String userId);

    boolean existsByTitle(String title);

    @Query("{$and:[" +
            "{$or:[{ 'title': {$regex: ?0, $options: 'i'} }, { $where: '?0 == \"\"' }]}," +
            "{$or:[{'completed': ?1}, { 'completed': null }]}," +
            "{$or:[{'priority.id': ?3}, { 'priority.id': null }]}," +
            "{$or:[{'category.id': ?4}, { 'category.id': null }]}," +
            "{$or:[{'taskDate': {$gte: ?5}}, { 'taskDate': null }]}," +
            "{$or:[{'taskDate': {$lte: ?6}}, { 'taskDate': null }]}," +
            "{'userId': ?2}" +
            "]}")
    Page<Task> findTaskByParams(String title, Boolean completed, String userId, String priorityId,
                                String categoryId, Date dateFrom, Date dateTo, Pageable pageable);
}
