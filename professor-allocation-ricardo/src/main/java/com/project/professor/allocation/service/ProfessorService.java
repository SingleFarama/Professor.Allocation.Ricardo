package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;
    private DepartmentService departmentService;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> findByDepartmentId(Long departmentId)
    {
        if (departmentId != null)
        {
            List<Professor> professors = professorRepository.findByDepartmentId(departmentId);
            return professors;
        }
        else
        {
            return null;
        }
    }

    public Professor findById(Long id)
    {
        if (id != null)
        {
            Optional<Professor> professorOptional = professorRepository.findById(id);
            Professor professor = professorOptional.orElse(null);
            return professor;
        }
        else
        {
            return null;
        }
    }

    public List<Professor> findAll()
    {
        List<Professor> professors = professorRepository.findAll();
        return professors;
    }

    public Professor create(Professor professor)
    {
        professor.setId(null);
        Professor professorNew = saveInternal(professor);
        return professorNew;
    }

    public Professor update(Professor professor)
    {
        Long id = professor.getId();

        if (id != null && professorRepository.existsById(id))
        {
            Professor professorNew = saveInternal(professor);
            return professorNew;
        }
        else
        {
            return null;
        }
    }

    public void deleteById(Long id)
    {
        if (id != null && professorRepository.existsById(id))
        {
            professorRepository.deleteById(id);
        }
    }

    public void deleteAll()
    {
        professorRepository.deleteAll();
    }

    private Professor saveInternal(Professor professor) {

            professor = professorRepository.save(professor);

            Department department = departmentService.findById(professor.getDepartmentId());
            professor.setDepartment(department);


            return professor;
        }
    }

