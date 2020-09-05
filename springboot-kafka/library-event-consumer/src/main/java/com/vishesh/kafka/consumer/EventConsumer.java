package com.vishesh.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishesh.kafka.domain.LibraryEvent;
import com.vishesh.kafka.service.LibraryEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {

  @Autowired
  LibraryEventService libraryEventService;

  @Autowired
  private ObjectMapper objectMapper;

  public static final String LIBRARY_EVENTS_TOPIC = "library-events";

  @KafkaListener(topics = {LIBRARY_EVENTS_TOPIC})
  public void onMessage(ConsumerRecord<Long, String> consumerRecord) throws JsonProcessingException {
    log.info("ConsumerRecord : {}", consumerRecord);
    LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
    log.info("LibraryEvent : {}", libraryEvent);
    libraryEventService.save(libraryEvent);
  }
}
