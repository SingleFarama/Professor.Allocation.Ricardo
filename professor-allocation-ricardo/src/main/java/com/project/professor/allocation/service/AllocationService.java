package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllocationService {

    private AllocationRepository allocationRepository;
    private ProfessorService professorService;
    private CourseService courseService;

    public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
                             CourseService courseService) {
        super();
        this.allocationRepository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
    }

    public List<Allocation> findByProfessorId(Long professorId)
    {
        if (professorId != null)
        {
            List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
            return allocations;
        } else {
            return null;
        }
    }

    public List<Allocation> findByCourseId(Long courseId)
    {
        if (courseId != null)
        {
            List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
            return allocations;
        }
        else
        {
            return null;
        }
    }

    public Allocation findById(Long id)
    {
        if (id != null)
        {
            Optional<Allocation> allocationOptional = allocationRepository.findById(id);
            Allocation allocation = allocationOptional.orElse(null);
            return allocation;
        }
        else
        {
            return null;
        }
    }

    public List<Allocation> findAll()
    {
        List<Allocation> allocations = allocationRepository.findAll();
        return allocations;
    }

    public Allocation create(Allocation allocation)
    {
        allocation.setId(null);
        Allocation allocationNew = saveInternal(allocation);
        return allocationNew;
    }

    public Allocation update(Allocation allocation)
    {
        Long id = allocation.getId();

        if (id != null && allocationRepository.existsById(id)) {
            Allocation allocationNew = saveInternal(allocation);
            return allocationNew;
        } else {
            return null;
        }
    }

    public void deleteById(Long id)
    {
        if (id != null && allocationRepository.existsById(id))
        {
            allocationRepository.deleteById(id);
        }
    }

    public void deleteAll()
    {
        allocationRepository.deleteAll();
    }

    private Allocation saveInternal(Allocation allocation) {
        if (hasCollision(allocation)) {
            throw new RuntimeException();
        } else {
            allocation = allocationRepository.save(allocation);

            Professor professor = professorService.findById(allocation.getProfessorId());
            allocation.setProfessor(professor);

            Course course = courseService.findById(allocation.getCourseId());
            allocation.setCourse(course);

            return allocation;
        }
    }

    boolean isEndHourGreaterThanStartHour(Allocation allocation) {
        return allocation != null && allocation.getStartHour() != null && allocation.getEndHour() != null
                && allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
    }

    boolean hasCollision(Allocation newAllocation) {
        boolean hasCollision = false;

        List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

        for (Allocation currentAllocation : currentAllocations) {
            hasCollision = hasCollision(currentAllocation, newAllocation);
            if (hasCollision) {
                break;
            }
        }

        return hasCollision;
    }

    private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
        return !currentAllocation.getId().equals(newAllocation.getId())
                && currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
                && currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
                && newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
    }
}