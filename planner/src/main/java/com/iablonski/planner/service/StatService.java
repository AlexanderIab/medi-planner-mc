package com.iablonski.planner.service;

import com.iablonski.planner.dto.StatDTO;

public interface StatService {
    StatDTO findStat(String userId);
}
