package com.universidade.sistemacrud.service;

import com.universidade.sistemacrud.model.Student;
import com.universidade.sistemacrud.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void deveSalvarEstudanteComSucesso() {
        Student estudante = new Student();
        estudante.setName("Matheus Teste");
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(estudante);

        Student estudanteSalvo = studentRepository.save(estudante);

        Assertions.assertNotNull(estudanteSalvo);
        Assertions.assertEquals("Matheus Teste", estudanteSalvo.getName());
    }

    @Test
    public void deveBuscarEstudantePorId() {
        Student estudante = new Student();
        estudante.setName("Matheus Teste");
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(estudante));

        Optional<Student> resultado = studentRepository.findById(1L);

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("Matheus Teste", resultado.get().getName());
    }
}