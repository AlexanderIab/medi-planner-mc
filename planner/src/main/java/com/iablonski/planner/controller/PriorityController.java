package com.iablonski.planner.controller;

import com.iablonski.planner.dto.PriorityDTO;
import com.iablonski.planner.response.MessageResponse;
import com.iablonski.planner.search.PrioritySearchValues;
import com.iablonski.planner.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/priority")
public class PriorityController {

    private final PriorityService priorityService;

    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostMapping("/id")
    public ResponseEntity<PriorityDTO> getPriorityById(@RequestBody String id){
        return new ResponseEntity<>(priorityService.getPriorityById(id), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<PriorityDTO>> getPrioritiesByUserEmail(@RequestBody String userId){
        List<PriorityDTO> priorities = priorityService.getPrioritiesByUserId(userId);
        return new ResponseEntity<>(priorities, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createPriority(@RequestBody PriorityDTO priorityDTO){
        priorityService.createPriority(priorityDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully created"), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updatePriority(@RequestBody PriorityDTO priorityDTO){
        priorityService.updatePriority(priorityDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<MessageResponse> deletePriority (@RequestBody String id){
        priorityService.deletePriorityById(id);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PriorityDTO>> searchPriority(@RequestBody PrioritySearchValues values){
        List<PriorityDTO> priorities = priorityService.findByTitle(values.title(), values.userId());
        return new ResponseEntity<>(priorities, HttpStatus.OK);
    }
}