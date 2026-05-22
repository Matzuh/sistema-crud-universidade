package com.universidade.sistemacrud.controller;

import com.universidade.sistemacrud.model.Professor;
import com.universidade.sistemacrud.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor savedProfessor = professorService.save(professor);
        return new ResponseEntity<>(savedProfessor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        List<Professor> professors = professorService.findAll();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return professorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professorDetails) {
        try {
            Professor updatedProfessor = professorService.update(id, professorDetails);
            return ResponseEntity.ok(updatedProfessor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        try {
            professorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}