package com.yanchware.fractal;

import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sharedconfig.SharedConfig;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppServiceDotnetSampleTest extends BaseTest {

  @Test
  public void validateLiveSystem() {
    var configuration = SharedConfig.getInstance(true);
    LiveSystem liveSystem = AppServiceDotnetSample.getLiveSystem(configuration);
    Collection<String> errors = liveSystem.validate();
    assertTrue(errors.isEmpty());
  }
}