package com.yanchware.fractal.microservice.sample.configuration;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AzureEnvVarConfiguration implements Configuration {

  private AzureEnvVarConfiguration() { }

  public static Configuration getInstance() {
    return new AzureEnvVarConfiguration();
  }

  @Override
  public String getLiveSystemName() {
    var liveSystemName = System.getenv("LIVE_SYSTEM_NAME");
    return isBlank(liveSystemName)
      ? "local-test-env-azure"
      : liveSystemName;
  }

  @Override
  public String getEnvironmentId() {
    var subscriptionId = System.getenv("SUBSCRIPTION_ID");
    if(isBlank(subscriptionId)) {
      throw new IllegalArgumentException("The environment variable SUBSCRIPTION_ID is required and it has not been defined");
    }

    return subscriptionId;
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
  public String getEnvironmentParentId() {
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
