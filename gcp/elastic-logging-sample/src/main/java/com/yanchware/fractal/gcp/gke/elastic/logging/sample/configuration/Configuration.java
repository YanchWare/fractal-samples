package com.yanchware.fractal.gcp.gke.elastic.logging.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getSubscriptionId();
  String getTenantId();
  String getEnvironmentDisplayName();
}
