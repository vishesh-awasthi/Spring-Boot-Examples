package com.vishesh.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.vishesh.entities.Student;
import com.vishesh.entities.StudentInput;
import com.vishesh.service.StudentService;
import org.springframework.stereotype.Component;

@Component
public class StudentMutation implements GraphQLMutationResolver {

  private StudentService studentService;

  public StudentMutation(StudentService studentService) {
    this.studentService = studentService;
  }

  public Student newStudent(StudentInput studentInput) {
    return studentService.saveStudent(studentInput);
  }

  public Student updateStudent(StudentInput studentInput) {
    return studentService.updateStudent(studentInput);
  }
}
