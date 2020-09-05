package com.vishesh.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * The persistent class for the student database table.
 */
@Data
@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String lastName;

  @JsonManagedReference
  @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
  private List<StudentFamily> studentFamily= new ArrayList<>();

  public void addStudentFamily(StudentFamily studentFamily){
    this.studentFamily.add(studentFamily);
    studentFamily.setStudent(this);
  }
}