package com.vishesh.entities;

import lombok.Data;

import java.util.List;

@Data
public class StudentInput {

  private long id;
  private String firstName;
  private String lastName;
  private List<StudentFamilyInput> studentFamily;
}
