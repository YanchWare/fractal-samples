package com.yanchware.fractal;

import com.yanchware.fractal.azure.sharedconfig.tests.AzureBaseTest;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultipleResourcesSampleTest extends AzureBaseTest {

  @Test
  public void validateLiveSystem() throws InstantiatorException {
    var configuration = SharedConfig.getInstance(true);
    var liveSystem = MultipleResourcesSample.getLiveSystem(Automaton.getInstance(), configuration);
    var errors = liveSystem.validate();

    assertTrue(errors.isEmpty());
    assertEquals(liveSystem.getId().name(), "MultipleResourcesSampleTest");
  }
}