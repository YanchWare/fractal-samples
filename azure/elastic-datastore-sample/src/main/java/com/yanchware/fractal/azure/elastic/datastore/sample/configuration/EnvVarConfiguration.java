package com.yanchware.fractal.azure.elastic.datastore.sample.configuration;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class EnvVarConfiguration implements Configuration {

  private EnvVarConfiguration() {
  }

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
    if (isBlank(resourceGroupId)) {
      throw new IllegalArgumentException("The environment variable RESOURCE_GROUP_ID is required and it has not been defined");
    }

    return resourceGroupId;
  }

  @Override
  public String getEnvironmentId() {
    var environmentId = System.getenv("ENVIRONMENT_ID");
    if (isBlank(environmentId)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_ID is required and it has not been defined");
    }

    return environmentId;
  }

  @Override
  public String getEnvironmentOwnerId() {
    var environmentOwnerId = System.getenv("ENVIRONMENT_OWNER_ID");
    if (isBlank(environmentOwnerId)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_OWNER_ID is required and it has not been defined");
    }

    return environmentOwnerId;
  }

  @Override
  public String getEnvironmentType() {
    var environmentType = System.getenv("ENVIRONMENT_TYPE");
    if (isBlank(environmentType)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_TYPE is required and it has not been defined");
    }

    return environmentType;
  }
}
