package com.yanchware.fractal.microservice.sample;

import com.yanchware.fractal.microservice.sample.configuration.AzureEnvVarConfiguration;
import com.yanchware.fractal.microservice.sample.configuration.GcpEnvVarConfiguration;
import com.yanchware.fractal.microservice.sample.livesystems.AzureDemoLiveSystem;
import com.yanchware.fractal.microservice.sample.livesystems.GcpDemoLiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

public class MicroserviceSample {

  public static void main (String[] args) throws InstantiatorException {

    // Instantiate live-system on Azure:
    AzureDemoLiveSystem.Instantiate(AzureEnvVarConfiguration.getInstance());

    // Instantiate live-system on Google:
    GcpDemoLiveSystem.Instantiate(GcpEnvVarConfiguration.getInstance());

  }
}
