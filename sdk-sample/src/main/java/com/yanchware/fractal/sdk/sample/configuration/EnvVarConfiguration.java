package com.yanchware.fractal.sdk.sample.configuration;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class EnvVarConfiguration implements Configuration {

  private EnvVarConfiguration() { }

  public static Configuration getInstance() {
    return new EnvVarConfiguration();
  }

  @Override
  public String getLiveSystemName() {
    var liveSystemName = System.getenv("LIVE_SYSTEM_NAME");
    return isBlank(liveSystemName)
      ? "local-test-env"
      : liveSystemName;
  }

  @Override
  public String getResourceGroupId() {
    var resourceGroupId = System.getenv("RESOURCE_GROUP_ID");
    if(isBlank(resourceGroupId)) {
      throw new IllegalArgumentException("The environment variable RESOURCE_GROUP_ID is required and it has not been defined");
    }

    return resourceGroupId;
  }

  @Override
  public String getSubscriptionId() {
    var subscriptionId = System.getenv("SUBSCRIPTION_ID");
    if(isBlank(subscriptionId)) {
      throw new IllegalArgumentException("The environment variable SUBSCRIPTION_ID is required and it has not been defined");
    }

    return subscriptionId;
  }

  @Override
  public String getTenantId() {
    var tenantId = System.getenv("TENANT_ID");
    if(isBlank(tenantId)) {
      throw new IllegalArgumentException("The environment variable TENANT_ID is required and it has not been defined");
    }

    return tenantId;
  }

  @Override
  public String getEnvironmentDisplayName() {
    var environmentDisplayName = System.getenv("ENV_DISPLAY_NAME");
    return isBlank(environmentDisplayName)
      ? "Locally deployed environment"
      : environmentDisplayName;
  }
}
