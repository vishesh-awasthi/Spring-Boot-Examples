package com.vishesh.repository;

import com.vishesh.entities.Student;
import com.vishesh.entities.StudentFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentFamilyRepository extends JpaRepository<StudentFamily, Long> {
  Optional<List<StudentFamily>> findAllByStudent(Student student);
}
