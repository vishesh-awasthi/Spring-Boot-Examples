package com.vishesh.kafka.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class LibraryEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long eventId;

  @OneToOne(mappedBy = "libraryEvent", cascade = CascadeType.ALL)
  @ToString.Exclude
  private Book book;

}
