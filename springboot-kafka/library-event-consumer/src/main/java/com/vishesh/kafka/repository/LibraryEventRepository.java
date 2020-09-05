package com.vishesh.kafka.repository;

import com.vishesh.kafka.domain.LibraryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryEventRepository extends JpaRepository<LibraryEvent, Long> {
}
