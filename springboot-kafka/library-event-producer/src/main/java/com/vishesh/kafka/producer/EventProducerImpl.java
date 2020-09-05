package com.vishesh.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishesh.kafka.domain.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class EventProducerImpl implements EventProducer {

  private KafkaTemplate<Long, String> kafkaTemplate;
  private ObjectMapper objectMapper;

  /**
   * Sends the event to default topic.
   *
   * @param event as event
   * @throws JsonProcessingException
   */
  @Override
  public void sendEvent(Event event) throws JsonProcessingException {
    long key = Objects.nonNull(event.getEventId()) ? event.getEventId() : 0L;
    String eventPayload = objectMapper.writeValueAsString(event);
    ListenableFuture<SendResult<Long, String>> listenableFuture = kafkaTemplate.sendDefault(key, eventPayload);
    handleCallBack(key, eventPayload, listenableFuture);
  }

  private void handleCallBack(long key, String eventPayload,
      ListenableFuture<SendResult<Long, String>> listenableFuture) {
    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Long, String>>() {
      @Override
      public void onFailure(Throwable throwable) {

      }

      @Override
      public void onSuccess(SendResult<Long, String> sendResult) {
        handleSuccess(key, eventPayload, sendResult);
      }
    });
  }

  private void handleSuccess(long key, String eventPayload, SendResult<Long, String> sendResult) {
    log.info("Message sent successfully for the key : {} and value is : {} , partition is : {} ", key, eventPayload,
        sendResult.getRecordMetadata().partition());
  }

}