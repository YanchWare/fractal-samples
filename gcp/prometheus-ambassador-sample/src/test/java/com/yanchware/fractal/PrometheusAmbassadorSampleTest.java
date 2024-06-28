package com.yanchware.fractal;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfig;
import com.yanchware.fractal.gcp.sharedconfig.tests.GcpBaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrometheusAmbassadorSampleTest extends GcpBaseTest {
  @Test
  public void validateLiveSystem() {
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = PrometheusAmbassadorSample.getLiveSystem(configuration);
    var errors = liveSystem.validate();

    assertTrue(errors.isEmpty());
    assertEquals(liveSystem.getName(), "PrometheusAmbassadorSampleTest");
  }
}