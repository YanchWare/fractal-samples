package com.yanchware.fractal.microservice.sample.configuration;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class GcpEnvVarConfiguration implements Configuration {

  private GcpEnvVarConfiguration() { }

  public static Configuration getInstance() {
    return new GcpEnvVarConfiguration();
  }

  @Override
  public String getLiveSystemName() {
    var liveSystemName = System.getenv("LIVE_SYSTEM_NAME_GCP");
    return isBlank(liveSystemName)
      ? "local-test-env-gcp"
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
  public String getEnvironmentId() {
    var subscriptionId = System.getenv("PROJECT_NAME");
    if(isBlank(subscriptionId)) {
      throw new IllegalArgumentException("The environment variable PROJECT_NAME is required and it has not been defined");
    }

    return subscriptionId;
  }

  @Override
  public String getEnvironmentParentId() {
    var tenantId = System.getenv("ORGANIZATION_ID");
    if(isBlank(tenantId)) {
      throw new IllegalArgumentException("The environment variable ORGANIZATION_ID is required and it has not been defined");
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
