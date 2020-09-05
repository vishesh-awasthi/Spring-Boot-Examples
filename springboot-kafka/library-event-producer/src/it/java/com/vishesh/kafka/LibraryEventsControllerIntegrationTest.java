package com.vishesh.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishesh.kafka.domain.Book;
import com.vishesh.kafka.domain.LibraryEvent;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"library-events"})
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "spring.kafka.admin.bootstrap.servers=${spring.embedded.kafka.brokers}"})
public class LibraryEventsControllerIntegrationTest {

  public static final String LIBRARY_EVENT_URL = "/v1/events/books";
  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  EmbeddedKafkaBroker kafkaBroker;

  @Autowired
  ObjectMapper objectMapper;

  private Consumer<Long, String> consumer;

  @BeforeEach
  void setUp() {
    Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", kafkaBroker));
    consumer = new DefaultKafkaConsumerFactory<>(configs, new LongDeserializer(),
        new StringDeserializer()).createConsumer();
    kafkaBroker.consumeFromAllEmbeddedTopics(consumer);
  }

  @AfterEach
  void tearDown() {
    consumer.close();
  }

  @Test
  public void test_postLibraryEvent_returnSuccess() {
    HttpEntity<LibraryEvent> libraryEventHttpEntity = new HttpEntity<>(getLibraryEvent());

    ResponseEntity<LibraryEvent> responseEntity =
        restTemplate.exchange(LIBRARY_EVENT_URL, HttpMethod.POST, libraryEventHttpEntity, LibraryEvent.class);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    ConsumerRecord<Long, String> record = KafkaTestUtils.getSingleRecord(consumer, "library-events", 10000L);
    String expectedRecord =
        "{\"eventId\":null,\"book\":{\"id\":1,\"name\":\"Spring with kafka\",\"author\":\"Vishesh\"}}";
    assertEquals(expectedRecord, record.value());
  }

  private static LibraryEvent getLibraryEvent() {
    return LibraryEvent.builder()
        .book(getBook())
        .build();
  }

  private static Book getBook() {
    return Book.builder()
        .id(1)
        .name("Spring with kafka")
        .author("Vishesh")
        .build();
  }

}
