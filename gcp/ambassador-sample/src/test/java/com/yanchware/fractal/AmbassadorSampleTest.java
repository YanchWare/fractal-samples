package com.yanchware.fractal;


import com.yanchware.fractal.gcp.sharedconfig.SharedConfig;
import com.yanchware.fractal.gcp.sharedconfig.tests.GcpBaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AmbassadorSampleTest extends GcpBaseTest {
  @Test
  public void validateLiveSystem() {
    System.setProperty("LIVE_SYSTEM_NAME", "AmbassadorSampleTest");
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = AmbassadorSample.getLiveSystem(configuration);
    var errors = liveSystem.validate();
    
    assertTrue(errors.isEmpty());
    assertEquals(liveSystem.getName(), "AmbassadorSampleTest");
  }
}