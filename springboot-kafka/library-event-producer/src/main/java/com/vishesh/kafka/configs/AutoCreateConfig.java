package com.vishesh.kafka.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class AutoCreateConfig {

  public static final String LIBRARY_EVENTS = "library-events";

  /**
   * This auto creates the kafka topic using the {@link org.apache.kafka.clients.admin.AdminClient}.
   *
   * @return {@link NewTopic}
   */
  @Bean
  public NewTopic newTopic() {
    return TopicBuilder
        .name(LIBRARY_EVENTS)
        .partitions(4)
        .replicas(1)
        .build();
  }
}
