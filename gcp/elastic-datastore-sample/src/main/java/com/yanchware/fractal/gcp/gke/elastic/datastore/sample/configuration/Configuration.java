package com.yanchware.fractal.gcp.gke.elastic.datastore.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getProjectId();
  String getOrganizationId();
  String getEnvironmentDisplayName();
}
