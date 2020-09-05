package com.vishesh.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * The persistent class for the student_family database table.
 */
@Data
@Entity
public class StudentFamily {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fatherName;

  private String motherName;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @Override
  public String toString() {
    return "StudentFamily{" +
        "id=" + id +
        ", fatherName='" + fatherName + '\'' +
        ", motherName='" + motherName + '\'' +
        '}';
  }
}