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

package com.github.sonus21.rqueue.metrics;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RqueueCounterTest {
  private QueueCounter queueCounter = mock(QueueCounter.class);
  private RqueueCounter rqueueCounter = new RqueueCounter(queueCounter);
  private String queueName = "test";

  @Test
  public void updateFailureCount() {
    rqueueCounter.updateFailureCount(queueName);
    verify(queueCounter, times(1)).updateFailureCount(queueName);
  }

  @Test
  public void updateExecutionCount() {
    rqueueCounter.updateExecutionCount(queueName);
    verify(queueCounter, times(1)).updateExecutionCount(queueName);
  }
}
