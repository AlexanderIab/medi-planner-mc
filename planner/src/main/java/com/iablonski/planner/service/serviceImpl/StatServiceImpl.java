package com.iablonski.planner.service.serviceImpl;

import com.iablonski.planner.domain.Stat;
import com.iablonski.planner.dto.StatDTO;
import com.iablonski.planner.mapper.StatMapper;
import com.iablonski.planner.repository.StatRepo;
import com.iablonski.planner.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepo statRepo;
    private final StatMapper statMapper;

    @Autowired
    public StatServiceImpl(StatRepo statRepo, StatMapper statMapper) {
        this.statRepo = statRepo;
        this.statMapper = statMapper;
    }

    @Override
    public StatDTO findStat(String userId) {
        Stat stat = statRepo.findByUserId(userId).orElseThrow();
        return statMapper.toDTO(stat);
    }
}