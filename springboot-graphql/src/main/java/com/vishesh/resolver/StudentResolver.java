package com.vishesh.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.vishesh.entities.Student;
import com.vishesh.entities.StudentFamily;
import com.vishesh.repository.StudentFamilyRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StudentResolver implements GraphQLResolver<Student> {

  private StudentFamilyRepository studentFamilyRepository;

  public StudentResolver(StudentFamilyRepository studentFamilyRepository) {
    this.studentFamilyRepository = studentFamilyRepository;
  }

  public List<StudentFamily> studentFamily(Student student) {
    return studentFamilyRepository.findAllByStudent(student)
        .orElse(Collections.EMPTY_LIST);
  }
}
