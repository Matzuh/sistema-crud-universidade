package com.universidade.sistemacrud.service;

import com.universidade.sistemacrud.model.Professor;
import com.universidade.sistemacrud.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        if (professor.getAddresses() != null) {
            professor.getAddresses().forEach(address -> address.setPerson(professor));
        }
        return professorRepository.save(professor);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    public Professor update(Long id, Professor professorDetails) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + id));

        professor.setName(professorDetails.getName());
        professor.setPhoneNumber(professorDetails.getPhoneNumber());
        professor.setEmailAddress(professorDetails.getEmailAddress());
        professor.setStatus(professorDetails.getStatus());
        professor.setSalary(professorDetails.getSalary());

        if (professorDetails.getAddresses() != null) {
            professor.getAddresses().clear();
            professorDetails.getAddresses().forEach(address -> {
                address.setPerson(professor);
                professor.getAddresses().add(address);
            });
        }

        return professorRepository.save(professor);
    }

    public void deleteById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + id));
        professorRepository.delete(professor);
    }
}