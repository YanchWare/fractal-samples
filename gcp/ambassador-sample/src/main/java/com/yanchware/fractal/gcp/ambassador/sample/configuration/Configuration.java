package com.yanchware.fractal.gcp.ambassador.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getProjectId();
  String getOrganizationId();
  String getEnvironmentDisplayName();
}
