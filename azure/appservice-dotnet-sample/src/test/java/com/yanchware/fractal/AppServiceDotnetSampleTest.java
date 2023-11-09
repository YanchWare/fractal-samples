package com.yanchware.fractal;

import com.yanchware.fractal.azure.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class AppServiceDotnetSampleTest extends BaseTest {

  @Test
  public void validateLiveSystem() {
    var configuration = EnvVarConfiguration.getInstance(true);
    LiveSystem liveSystem = AppServiceDotnetSample.getLiveSystem(configuration);
    Collection<String> errors = liveSystem.validate();
    assertTrue(errors.isEmpty());
  }
}