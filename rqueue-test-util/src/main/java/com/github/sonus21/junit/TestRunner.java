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

package com.github.sonus21.junit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TestRunner {
  private TestRunner() {}

  public interface Test {
    void run() throws Exception;
  }

  public static void run(Test test, Test failureCallback) throws Exception {
    run(test, failureCallback, 0);
  }

  public static void run(Test test, Test failureCallback, int retryCount) throws Exception {
    int iteration = 1;
    while (true) {
      log.info("Running test, Iteration: {}", iteration);
      try {
        test.run();
        return;
      } catch (Exception e) {
        log.error("Test failed", e);
        if (failureCallback != null) {
          failureCallback.run();
        }
        if (retryCount == iteration - 1) {
          throw e;
        }
      }
      iteration += 1;
    }
  }
}
