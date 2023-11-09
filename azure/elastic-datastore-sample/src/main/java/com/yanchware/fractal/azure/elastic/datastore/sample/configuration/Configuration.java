package com.yanchware.fractal.azure.elastic.datastore.sample.configuration;

public interface Configuration {
  String getLiveSystemName();

  String getResourceGroupId();

  String getEnvironmentId();

  String getEnvironmentOwnerId();

  String getEnvironmentType();
}