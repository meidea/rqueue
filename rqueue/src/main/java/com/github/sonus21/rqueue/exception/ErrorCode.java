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

package com.github.sonus21.rqueue.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
  SUCCESS(0, "Success"),
  ERROR(1, "Error"),
  VALIDATION_ERROR(2, "Validation error"),
  CONCURRENT_REQUEST(101, "Concurrent request"),
  TOPIC_LIST_CAN_NOT_BE_EMPTY(102, "Topic list cannot be empty"),
  TOPIC_ALREADY_EXIST(103, "Topic already exist."),
  DUPLICATE_TOPIC(104, "Duplicate topic"),
  TOPIC_DOES_NOT_EXIST(105, "Topic does not exist"),
  DUPLICATE_SUBSCRIPTION(106, "Already subscribed"),
  SUBSCRIPTION_DOES_NOT_EXIST(107, "Already subscribed"),
  NO_MESSAGE_PROVIDED(107, "No message provided");
  private int code;
  private String message;

  @JsonValue
  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}