package com.vishesh.service.impl;

import com.vishesh.entities.Student;
import com.vishesh.entities.StudentFamily;
import com.vishesh.entities.StudentInput;
import com.vishesh.exceptions.ResourceNotFoundException;
import com.vishesh.repository.StudentFamilyRepository;
import com.vishesh.repository.StudentRepository;
import com.vishesh.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

  @Resource
  private StudentRepository studentRepository;

  @Resource
  private StudentFamilyRepository studentFamilyRepository;

  public Student getStudent(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : {}" + id));
  }

  @Override
  public Student saveStudent(StudentInput studentInput) {
    Student newStudent = new Student();
    newStudent.setFirstName(studentInput.getFirstName());
    newStudent.setLastName(studentInput.getLastName());
    ArrayList<StudentFamily> studentFamilies = new ArrayList<>();
    studentInput.getStudentFamily().forEach(studentFamilyInput -> {
      StudentFamily studentFamily = new StudentFamily();
      studentFamily.setFatherName(studentFamilyInput.getFatherName());
      studentFamily.setMotherName(studentFamilyInput.getMotherName());
      studentFamily.setStudent(newStudent);
      newStudent.addStudentFamily(studentFamily);
    });
    return studentRepository.save(newStudent);
  }

  @Override
  public Student updateStudent(StudentInput studentInput) {
    Student student = studentRepository.findById(studentInput.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : {}", studentInput.getId()));
    student.setLastName(studentInput.getLastName());
    student.setFirstName(studentInput.getFirstName());
    return studentRepository.save(student);
  }

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

}
