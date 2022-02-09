package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.AllocationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }
}
