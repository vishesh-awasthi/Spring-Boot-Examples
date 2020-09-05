package com.vishesh.kafka.consumer;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vishesh.kafka.domain.LibraryEvent;
import com.vishesh.kafka.service.LibraryEventService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"library-events"}, partitions = 4)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
public class EventConsumerIntegrationTest {

  @Autowired
  EmbeddedKafkaBroker kafkaBroker;

  @Autowired
  private KafkaTemplate<Long, String> kafkaTemplate;

  @Autowired
  private KafkaListenerEndpointRegistry endpointRegistry;

  @SpyBean
  EventConsumer eventConsumer;

  @SpyBean
  LibraryEventService libraryEventService;

  @BeforeEach
  void setUp() {
    for (MessageListenerContainer messageListenerContainer : endpointRegistry.getListenerContainers()) {
      ContainerTestUtils.waitForAssignment(messageListenerContainer, kafkaBroker.getPartitionsPerTopic());
    }
  }

  @Test
  public void eventConsumeTest() throws ExecutionException, InterruptedException, JsonProcessingException {
    String message = "{\n" +
        "    \"eventId\": 214324234,\n" +
        "    \"book\": {\n" +
        "        \"id\": 1,\n" +
        "        \"name\": \"Test\",\n" +
        "        \"author\": \"vishesh\"\n" +
        "    }\n" +
        "}";
    kafkaTemplate.sendDefault(message).get();

    CountDownLatch latch = new CountDownLatch(1);
    latch.await(3, TimeUnit.SECONDS);

    verify(eventConsumer, times(1)).onMessage(isA(ConsumerRecord.class));
    verify(libraryEventService, times(1)).save(isA(LibraryEvent.class));
  }
}
