package com.vishesh.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vishesh.kafka.domain.LibraryEvent;
import com.vishesh.kafka.producer.EventProducerImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/events/books")
@AllArgsConstructor
public class LibraryEventsController {

  private EventProducerImpl eventProducer;

  @PostMapping()
  public ResponseEntity<LibraryEvent> addBook(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
    eventProducer.sendEvent(libraryEvent);
    return new ResponseEntity(libraryEvent, HttpStatus.CREATED);
  }

  @PutMapping()
  public ResponseEntity<LibraryEvent> updateBook(@RequestBody LibraryEvent libraryEvent) {
    return new ResponseEntity<>(libraryEvent, HttpStatus.OK);
  }
}
