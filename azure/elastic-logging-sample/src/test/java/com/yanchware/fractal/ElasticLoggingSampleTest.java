package com.yanchware.fractal;

import com.yanchware.fractal.azure.sharedconfig.tests.AzureBaseTest;
import com.yanchware.fractal.sharedconfig.SharedConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElasticLoggingSampleTest extends AzureBaseTest {

  @Test
  public void validateLiveSystem() {
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = ElasticLoggingSample.getLiveSystem(configuration);
    var errors = liveSystem.validate();

    assertTrue(errors.isEmpty());
    assertEquals(liveSystem.getName(), "ElasticLoggingSampleTest");
  }
}