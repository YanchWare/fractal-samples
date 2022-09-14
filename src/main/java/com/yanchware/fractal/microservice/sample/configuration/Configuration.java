package com.yanchware.fractal.microservice.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getEnvironmentId();
  String getEnvironmentParentId();
  String getEnvironmentDisplayName();
}
