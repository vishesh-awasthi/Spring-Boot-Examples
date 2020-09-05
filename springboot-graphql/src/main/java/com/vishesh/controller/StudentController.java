package com.vishesh.controller;

import com.vishesh.entities.Student;
import com.vishesh.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

  private StudentRepository studentRepository;

  @GetMapping("/students")
  public List<Student> getStudent() {
    return studentRepository.findAll();
  }
}
