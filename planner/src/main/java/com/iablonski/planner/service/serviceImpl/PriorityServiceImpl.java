package com.iablonski.planner.service.serviceImpl;

import com.iablonski.planner.domain.Priority;
import com.iablonski.planner.dto.PriorityDTO;
import com.iablonski.planner.mapper.PriorityMapper;
import com.iablonski.planner.repository.PriorityRepo;
import com.iablonski.planner.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepo priorityRepo;
    private final PriorityMapper priorityMapper;

    @Autowired
    public PriorityServiceImpl(PriorityRepo priorityRepo, PriorityMapper priorityMapper) {
        this.priorityRepo = priorityRepo;
        this.priorityMapper = priorityMapper;
    }


    @Override
    public PriorityDTO getPriorityById(String id) {
        Priority priority = priorityRepo.findById(id).orElseThrow();
        return priorityMapper.toDTO(priority);
    }

    @Override
    public void createPriority(PriorityDTO priorityDTO) {
        if(priorityRepo.existsByTitle(priorityDTO.title())){
            throw new RuntimeException("Already Exists");
        }
        Priority priority = new Priority();
        priority.setTitle(priorityDTO.title());
        priority.setColor(priorityDTO.color());
        priority.setUserId(priorityDTO.userId());
        priorityRepo.save(priority);
    }

    @Override
    public void updatePriority(PriorityDTO priorityDTO) {
        Priority priority = priorityRepo.findById(priorityDTO.id()).orElseThrow();
        priority.setTitle(priorityDTO.title());
        priority.setColor(priorityDTO.color());
        priority.setUserId(priorityDTO.userId());
        priorityRepo.save(priority);
    }

    @Override
    public void deletePriorityById(String id) {
        if (!priorityRepo.existsById(id)){
            throw new RuntimeException("Not Exists");
        }
        priorityRepo.deleteById(id);
    }

    @Override
    public List<PriorityDTO> getPrioritiesByUserId(String userId) {
        return priorityRepo.findByUserIdOrderByTitleAsc(userId).stream()
                .map(priorityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriorityDTO> findByTitle(String title, String userId) {
        return priorityRepo.findByTitle(title, userId).stream()
                .map(priorityMapper::toDTO)
                .collect(Collectors.toList());
    }
}