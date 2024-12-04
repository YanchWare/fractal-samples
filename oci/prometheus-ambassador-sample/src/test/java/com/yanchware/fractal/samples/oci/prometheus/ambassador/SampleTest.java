package com.yanchware.fractal.samples.oci.prometheus.ambassador;

import com.yanchware.fractal.oci.sharedconfig.OciBaseTest;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleTest extends OciBaseTest {

  @Test
  public void validateLiveSystem() throws InstantiatorException {
    var configuration = SharedConfig.getInstance(true);
    var automaton = Automaton.getInstance();
    var liveSystem = Sample.getLiveSystem(automaton, configuration);
    var errors = liveSystem.validate();

    assertTrue(errors.isEmpty());
    assertEquals(Sample.LIVE_SYSTEM_NAME, liveSystem.getId().name());
  }
}