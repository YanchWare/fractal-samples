package com.yanchware.fractal.gcp.gke.elastic.datastore.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getSubscriptionId();
  String getTenantId();
  String getEnvironmentDisplayName();
}
