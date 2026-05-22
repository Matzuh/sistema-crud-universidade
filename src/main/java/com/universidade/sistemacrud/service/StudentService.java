package com.universidade.sistemacrud.service;

import com.universidade.sistemacrud.model.Student;
import com.universidade.sistemacrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        if (student.getAddresses() != null) {
            student.getAddresses().forEach(address -> address.setPerson(student));
        }
        return studentRepository.save(student);
    }

    public org.springframework.data.domain.Page<Student> findAll(org.springframework.data.domain.Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student update(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com o ID: " + id));

        student.setName(studentDetails.getName());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setEmailAddress(studentDetails.getEmailAddress());
        student.setStatus(studentDetails.getStatus());
        student.setStudentNumber(studentDetails.getStudentNumber());
        student.setPhoto(studentDetails.getPhoto());

        if (studentDetails.getAddresses() != null) {
            student.getAddresses().clear();
            studentDetails.getAddresses().forEach(address -> {
                address.setPerson(student);
                student.getAddresses().add(address);
            });
        }

        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com o ID: " + id));
        studentRepository.delete(student);
    }
}