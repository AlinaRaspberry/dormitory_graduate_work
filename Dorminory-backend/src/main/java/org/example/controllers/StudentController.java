package org.example.controllers;

import org.example.models.Student;
import org.example.models.dto.StudentDto;
import org.example.models.requests.CreateStudentRequest;
import org.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controllers
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents().stream()
                .map(student -> new StudentDto(student.getStudentId(), student.getName(), student.getAverageScore(), student.getDateOfBirth()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<StudentDto> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> new StudentDto(student.getStudentId(), student.getName(), student.getAverageScore(), student.getDateOfBirth()));
    }

    @PostMapping
    public Student createStudent(@RequestBody CreateStudentRequest request) {
        Student student = new Student();
        student.setName(request.name());
        student.setAverageScore(request.averageScore());
        student.setDateOfBirth(request.dateOfBirth());
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
