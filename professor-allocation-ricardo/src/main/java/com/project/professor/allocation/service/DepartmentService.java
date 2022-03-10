package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ProfessorRepository professorRepository;

    public DepartmentService(DepartmentRepository departmentRepository, ProfessorRepository professorRepository) {
        super();
        this.departmentRepository = departmentRepository;
        this.professorRepository = professorRepository;
    }

    public Department findById(Long id) {
        if (id != null) {
            Optional<Department> departmentOptional = departmentRepository.findById(id);
            Department department = departmentOptional.orElse(null);
            return department;
        } else {
            return null;
        }
    }

    public List<Department> findAll(String name) {
        if (name == null) {
            return departmentRepository.findAll();
        } else {
            return departmentRepository.findByNameContaining(name);
        }
    }

    public Department create(Department department) {
        department.setId(null);
        Department departmentNew = departmentRepository.save(department);
        return departmentNew;
    }

    public Department update(Department department) {
        Long id = department.getId();

        if (id != null && departmentRepository.existsById(id)) {
            Department departmentNew = departmentRepository.save(department);
            return departmentNew;
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        if (id != null && departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
    }

    public void deleteAll() {
        departmentRepository.deleteAll();
    }

    private Department saveInternal(Department department) {
        department = departmentRepository.save(department);

        List<Professor> professors = professorRepository.findByDepartmentId(department.getId());
        department.setProfessors(professors);

        return department;
    }
}
