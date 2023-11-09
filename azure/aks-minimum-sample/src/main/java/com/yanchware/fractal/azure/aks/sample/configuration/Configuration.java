package com.yanchware.fractal.azure.aks.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getEnvironmentId();
  String getEnvironmentOwnerId();
  String getEnvironmentType();
}