package com.yanchware.fractal;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfig;
import com.yanchware.fractal.gcp.sharedconfig.tests.GcpBaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostgreSqlSampleTest extends GcpBaseTest {
  @Test
  public void validateLiveSystem() {
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = PostgreSqlSample.getLiveSystem(configuration);
    var errors = liveSystem.validate();

    assertTrue(errors.isEmpty());
    assertEquals(liveSystem.getName(), "PostgreSqlSampleTest");
  }
}