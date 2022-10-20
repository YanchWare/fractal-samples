package com.yanchware.fractal.sdk.sample.configuration;

import com.yanchware.fractal.sdk.aggregates.Environment;

public class EnvironmentConfiguration {
  public static Environment getEnvironment(Configuration configuration) {
    return Environment.builder()
        .withId(configuration.getSubscriptionId())
        .withDisplayName(configuration.getEnvironmentDisplayName())
        .withParentId(configuration.getTenantId())
        .withParentType("tenant")
        .build();
  }
}
