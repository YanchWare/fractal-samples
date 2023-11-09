package com.yanchware.fractal.azure.sample.configuration;

public interface Configuration {
  String getLiveSystemName();

  String getResourceGroupId();

  String getEnvironmentId();

  String getEnvironmentOwnerId();

  String getEnvironmentType();
}