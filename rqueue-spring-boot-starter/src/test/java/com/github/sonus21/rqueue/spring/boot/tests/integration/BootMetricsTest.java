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

package com.github.sonus21.rqueue.spring.boot.tests.integration;

import com.github.sonus21.junit.SpringTestTracerExtension;
import com.github.sonus21.rqueue.exception.TimedOutException;
import com.github.sonus21.rqueue.spring.boot.application.Application;
import com.github.sonus21.rqueue.test.tests.MetricTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ExtendWith(SpringTestTracerExtension.class)
@ContextConfiguration(classes = Application.class)
@Slf4j
@TestPropertySource(
    properties = {
      "rqueue.retry.per.poll=20",
      "rqueue.scheduler.auto.start=false",
      "spring.redis.port=8003",
      "mysql.db.name=test4",
      "rqueue.metrics.count.failure=true",
      "rqueue.metrics.count.execution=true",
    })
public class BootMetricsTest extends MetricTest {

  @Test
  public void delayedQueueStatus() throws TimedOutException {
    this.verifyDelayedQueueStatus();
  }

  @Test
  @DisabledIfEnvironmentVariable(named = "CI_ENV", matches = "true")
  public void metricStatus() throws TimedOutException {
    this.verifyMetricStatus();
  }

  @Test
  public void countStatusTest() throws TimedOutException {
    this.verifyCountStatus();
  }
}
