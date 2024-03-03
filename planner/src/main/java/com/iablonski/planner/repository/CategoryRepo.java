package com.iablonski.planner.repository;

import com.iablonski.planner.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepo extends MongoRepository<Category, String> {
    List<Category> findByUserIdOrderByTitleAsc(String userId);

    @Query("{'title': {$regex: ?0, $options: 'i'}, 'userId': ?1}")
    List<Category> findByTitleAndUserId(String title, String userId);

    List<Category> findByTitle(String title);

    boolean existsByTitle(String title);
}