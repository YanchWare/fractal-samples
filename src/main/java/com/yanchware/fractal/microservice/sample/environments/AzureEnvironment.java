package com.yanchware.fractal.microservice.sample.environments;

import com.yanchware.fractal.microservice.sample.configuration.Configuration;
import com.yanchware.fractal.sdk.aggregates.Environment;

public class AzureEnvironment {

  public static Environment getEnvironment(Configuration configuration) {
    return Environment.builder()
        .withDisplayName(configuration.getEnvironmentDisplayName())
        .withId(configuration.getEnvironmentId())
        .withParentId(configuration.getEnvironmentParentId())
        .withParentType("tenant")
        .build();
  }

}
