package com.yanchware.fractal.samples.aws.ambassador;

import com.yanchware.fractal.samples.aws.sharedconfig.AwsBaseTest;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.samples.aws.sharedconfig.SharedConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleTest extends AwsBaseTest {

  @Test
  public void validateLiveSystem() throws InstantiatorException {
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = Sample.getLiveSystem(Automaton.getInstance(), configuration);
    var errors = liveSystem.validate();

    assertTrue(errors.isEmpty());
    assertEquals(Sample.LIVE_SYSTEM_NAME, liveSystem.getId().name());
  }
}