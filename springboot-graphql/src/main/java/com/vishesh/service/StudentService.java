package com.vishesh.service;

import com.vishesh.entities.Student;
import com.vishesh.entities.StudentInput;

import java.util.List;

public interface StudentService {

  List<Student> getAllStudents();

  Student getStudent(Long id);

  Student saveStudent(StudentInput student);

  Student updateStudent(StudentInput studentInput);

}
