package com.project.professor.allocation.service;


import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department findById(Long id)
    {
        if (id != null)
        {
            Optional<Department> departmentOptional = departmentRepository.findById(id);
            Department department = departmentOptional.orElse(null);
            return department;
        }
        else
        {
            return null;
        }
    }

    public List<Department> findAll()
    {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public Department create(Department department)
    {
        department.setId(null);
        Department departmentNew = departmentRepository.save(department);
        return departmentNew;
    }

    public Department update(Department department)
    {
        Long id = allocation.getId();

        if (id != null && allocationRepository.existsById(id))
        {
            Allocation allocationNew = saveInternal(allocation);
            return allocationNew;
        }
        else
        {
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
