/*
 * Copyright 2020 Sonu Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.sonus21.rqueue.test.application;

import com.github.sonus21.rqueue.config.SimpleRqueueListenerContainerFactory;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.util.MimeType;

public abstract class CustomMessageConverterApplication extends ApplicationBasicConfiguration {
  @PostConstruct
  public void postConstruct() {
    init();
  }

  @PreDestroy
  public void preDestroy() {
    destroy();
  }

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(redisHost, redisPort);
  }

  @Bean
  public SimpleRqueueListenerContainerFactory simpleRqueueListenerContainerFactory(
      RedisConnectionFactory redisConnectionFactory) {
    SimpleRqueueListenerContainerFactory simpleRqueueListenerContainerFactory =
        new SimpleRqueueListenerContainerFactory();
    simpleRqueueListenerContainerFactory.setRedisConnectionFactory(redisConnectionFactory);
    simpleRqueueListenerContainerFactory.setMessageConverters(
        Collections.singletonList(new MappingJackson2MessageConverter()));
    MessageHeaders messageHeaders =
        new MessageHeaders(
            Collections.singletonMap(
                MessageHeaders.CONTENT_TYPE, new MimeType("application", "json")));
    simpleRqueueListenerContainerFactory.setMessageHeaders(messageHeaders);
    return simpleRqueueListenerContainerFactory;
  }
}
