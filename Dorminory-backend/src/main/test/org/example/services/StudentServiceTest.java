package org.example.services;

import org.example.models.Student;
import org.example.models.Benefit;
import org.example.models.Dormitory;
import org.example.repositories.StudentRepository;
import org.example.repositories.BenefitRepository;
import org.example.repositories.DormitoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStudents_ShouldReturnAllStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        List<Student> result = studentService.getAllStudents();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById_ShouldReturnStudent_WhenFound() {
        Student student = new Student();
        student.setStudentId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.getStudentById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getStudentId());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void getStudentById_ShouldReturnEmpty_WhenNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Student> result = studentService.getStudentById(1L);

        assertFalse(result.isPresent());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void createStudent_ShouldSaveAndReturnStudent() {
        Student student = new Student();
        Student savedStudent = new Student();
        savedStudent.setStudentId(1L);
        when(studentRepository.save(student)).thenReturn(savedStudent);

        Student result = studentService.createStudent(student);

        assertNotNull(result);
        assertEquals(1L, result.getStudentId());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void deleteStudent_ShouldRemoveStudentById() {
        doNothing().when(studentRepository).deleteById(1L);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }
}

