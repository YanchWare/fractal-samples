package com.yanchware.fractal;

import com.yanchware.fractal.oci.sharedconfig.OciBaseTest;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OkeMinimumSampleTest extends OciBaseTest {

  @Test
  public void validateLiveSystem() throws InstantiatorException {
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = OkeMinimumSample.getLiveSystem(Automaton.getInstance(), configuration);
    var errors = liveSystem.validate();
    
    assertTrue(errors.isEmpty());
    assertEquals("OkeMinimumSampleTest", liveSystem.getId().name());
  }
}