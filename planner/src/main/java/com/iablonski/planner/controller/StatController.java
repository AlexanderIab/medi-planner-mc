package com.iablonski.planner.controller;

import com.iablonski.planner.dto.StatDTO;
import com.iablonski.planner.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stat")
public class StatController {

    private final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @PostMapping("/search")
    public ResponseEntity<StatDTO> findStat(@RequestBody String userId){
        return new ResponseEntity<>(statService.findStat(userId), HttpStatus.OK);
    }
}