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

package com.github.sonus21.rqueue.core.support;

import com.github.sonus21.rqueue.core.RqueueMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.GenericMessage;

public final class RqueueMessageUtils {
  private RqueueMessageUtils() {}

  private static final String META_DATA_KEY_PREFIX = "__rq::m-mdata::";
  private static final String KEY_SEPARATOR = "::";

  public static String getMessageMetaId(String queueName, String messageId) {
    return META_DATA_KEY_PREFIX + queueName + KEY_SEPARATOR + messageId;
  }

  public static Object convertMessageToObject(
      RqueueMessage message, MessageConverter messageConverter) {
    return convertMessageToObject(new GenericMessage<>(message.getMessage()), messageConverter);
  }

  public static Object convertMessageToObject(
      Message<String> message, MessageConverter messageConverter) {
    return messageConverter.fromMessage(message, null);
  }

  public static RqueueMessage buildMessage(
      MessageConverter converter,
      Object object,
      String queueName,
      Integer retryCount,
      Long delay,
      MessageHeaders messageHeaders) {
    Message<?> msg = converter.toMessage(object, messageHeaders);
    if (msg == null) {
      throw new MessageConversionException("Message could not be build (null)");
    }
    Object payload = msg.getPayload();
    if (payload instanceof String) {
      return new RqueueMessage(queueName, (String) payload, retryCount, delay);
    }
    if (payload instanceof byte[]) {
      return new RqueueMessage(queueName, new String((byte[]) msg.getPayload()), retryCount, delay);
    }
    throw new MessageConversionException("Message payload is neither String nor byte[]");
  }

  public static List<RqueueMessage> generateMessages(
      MessageConverter converter, String queueName, int count) {
    return generateMessages(converter, "Test Object", queueName, null, null, count);
  }

  public static List<RqueueMessage> generateMessages(
      MessageConverter converter, String queueName, long delay, int count) {
    return generateMessages(converter, "Test object", queueName, null, delay, count);
  }

  public static List<RqueueMessage> generateMessages(
      MessageConverter converter,
      Object object,
      String queueName,
      Integer retryCount,
      Long delay,
      int count) {
    List<RqueueMessage> messages = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      messages.add(buildMessage(converter, object, queueName, retryCount, delay, null));
    }
    return messages;
  }
}
