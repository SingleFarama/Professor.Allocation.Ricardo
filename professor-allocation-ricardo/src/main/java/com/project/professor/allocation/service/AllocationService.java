package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AllocationService {

    private AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    public Allocation findById(Long id)
    {
        Optional<Allocation> allocationOptional = allocationRepository.findById(id);
        Allocation allocation = allocationOptional.orElse(null);
        return allocation;
    }
}