package com.iablonski.planner.repository;

import com.iablonski.planner.domain.Priority;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PriorityRepo extends MongoRepository<Priority, String> {
    List<Priority> findByUserIdOrderByTitleAsc(String userId);

    @Query("{'title': {$regex: ?0, $options: 'i'}, 'userId': ?1}")
    List<Priority> findByTitle(String title, String userId);

    boolean existsByTitle(String title);
}
