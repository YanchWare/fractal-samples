package com.yanchware.fractal.gcp.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getProjectId();
  String getOrganizationId();
  String getEnvironmentDisplayName();
}
