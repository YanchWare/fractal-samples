package com.yanchware.fractal.microservice.sample;

import com.yanchware.fractal.microservice.sample.configuration.AzureEnvVarConfiguration;
import com.yanchware.fractal.microservice.sample.configuration.GcpEnvVarConfiguration;
import com.yanchware.fractal.microservice.sample.livesystems.AzureDemoLiveSystem;
import com.yanchware.fractal.microservice.sample.livesystems.GcpDemoLiveSystem;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

public class MicroserviceSample {

  public static void main (String[] args) throws InstantiatorException {

    // Instantiate Azure live-system:
    var azureLiveSystem = AzureDemoLiveSystem.build(AzureEnvVarConfiguration.getInstance());

    // Instantiate Google live-system:
    var gcpLiveSystem = GcpDemoLiveSystem.build(GcpEnvVarConfiguration.getInstance());

    Automaton.instantiate(List.of(azureLiveSystem, gcpLiveSystem));
  }
}
