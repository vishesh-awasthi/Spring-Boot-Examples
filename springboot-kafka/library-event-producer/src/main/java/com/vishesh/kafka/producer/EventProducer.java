package com.vishesh.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vishesh.kafka.domain.Event;

public interface EventProducer {
  void sendEvent(Event event) throws JsonProcessingException;
}
