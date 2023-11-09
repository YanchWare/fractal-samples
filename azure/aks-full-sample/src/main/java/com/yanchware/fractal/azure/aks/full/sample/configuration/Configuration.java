package com.yanchware.fractal.azure.aks.full.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getEnvironmentId();
  String getEnvironmentOwnerId();
  String getEnvironmentType();
}
