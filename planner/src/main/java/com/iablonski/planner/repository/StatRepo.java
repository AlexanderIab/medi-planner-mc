package com.iablonski.planner.repository;

import com.iablonski.planner.domain.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StatRepo extends MongoRepository<Stat, String> {
    Optional<Stat> findByUserId(String userId);
}
