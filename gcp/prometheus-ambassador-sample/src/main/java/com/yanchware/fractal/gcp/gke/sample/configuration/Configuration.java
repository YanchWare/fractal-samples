package com.yanchware.fractal.gcp.gke.sample.configuration;

public interface Configuration {
  String getLiveSystemName();
  String getResourceGroupId();
  String getSubscriptionId();
  String getTenantId();
  String getEnvironmentDisplayName();
}
