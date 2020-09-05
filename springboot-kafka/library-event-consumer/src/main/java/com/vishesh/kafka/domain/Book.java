package com.vishesh.kafka.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity
public class Book {

  @Id
  private long id;
  private String name;
  private String author;
  @OneToOne
  @JoinColumn(name = "eventId")
  private LibraryEvent libraryEvent;

}
