package com.vishesh.kafka.service;

import com.vishesh.kafka.domain.LibraryEvent;
import com.vishesh.kafka.repository.LibraryEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LibraryEventService {

  @Autowired
  private LibraryEventRepository libraryEventRepository;

  public void save(LibraryEvent libraryEvent) {
    libraryEvent.getBook().setLibraryEvent(libraryEvent);
    libraryEventRepository.save(libraryEvent);
  }
}
