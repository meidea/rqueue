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

package com.github.sonus21.rqueue.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import com.github.sonus21.rqueue.config.RqueueConfig;
import com.github.sonus21.rqueue.core.impl.RqueueEndpointManagerImpl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

public class RqueueEndpointManagerTest {
  private final RqueueEndpointManager rqueueEndpointManager =
      new RqueueEndpointManagerImpl(mock(RqueueMessageTemplate.class));
  RqueueConfig rqueueConfig = mock(RqueueConfig.class);

  @Before
  public void init() throws IllegalAccessException {
    FieldUtils.writeField(rqueueEndpointManager, "rqueueConfig", rqueueConfig, true);
  }

  @Test
  public void registerQueue() {

    rqueueEndpointManager.registerQueue("test", "high");
    rqueueEndpointManager.isQueueRegistered("test");
    rqueueEndpointManager.isQueueRegistered("test", "high");
  }

  @Test
  public void getQueueConfig() {
    rqueueEndpointManager.registerQueue("test2", "high");
    assertEquals(1, rqueueEndpointManager.getQueueConfig("test2").size());
  }
}
