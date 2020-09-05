package com.vishesh.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.vishesh.entities.Student;
import com.vishesh.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.annotation.Resource;

@Component
public class Query implements GraphQLQueryResolver {

  @Resource
  private StudentService studentService;

  public List<Student> allStudents() {
    return studentService.getAllStudents();
  }

  public Student getStudent(long id) {
    return studentService.getStudent(id);
  }
}
