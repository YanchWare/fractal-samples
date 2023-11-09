package com.yanchware.fractal.azure.aks.sample.configuration;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class EnvVarConfiguration implements Configuration {

  private boolean readFromProperties;

  private EnvVarConfiguration() {
    new EnvVarConfiguration(false);
  }

  public EnvVarConfiguration(boolean readFromProperties) {
    this.readFromProperties = readFromProperties;
  }

  public static Configuration getInstance() {
    return new EnvVarConfiguration();
  }

  public static Configuration getInstance(boolean readFromProperties) {
    return new EnvVarConfiguration(readFromProperties);
  }

  @Override
  public String getLiveSystemName() {
    var liveSystemName = getVariableValue("LIVE_SYSTEM_NAME");
    return isBlank(liveSystemName)
        ? "local-test-env"
        : liveSystemName;
  }

  @Override
  public String getResourceGroupId() {
    var resourceGroupId = getVariableValue("RESOURCE_GROUP_ID");
    if (isBlank(resourceGroupId)) {
      throw new IllegalArgumentException("The environment variable RESOURCE_GROUP_ID is required and it has not been defined");
    }

    return resourceGroupId;
  }

  @Override
  public String getEnvironmentId() {
    var environmentId = getVariableValue("ENVIRONMENT_ID");
    if (isBlank(environmentId)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_ID is required and it has not been defined");
    }

    return environmentId;
  }

  @Override
  public String getEnvironmentOwnerId() {
    var environmentOwnerId = getVariableValue("ENVIRONMENT_OWNER_ID");
    if (isBlank(environmentOwnerId)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_OWNER_ID is required and it has not been defined");
    }

    return environmentOwnerId;
  }

  @Override
  public String getEnvironmentType() {
    var environmentType = getVariableValue("ENVIRONMENT_TYPE");
    if (isBlank(environmentType)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_TYPE is required and it has not been defined");
    }

    return environmentType;
  }

  private String getVariableValue(String key) {

    var value = readFromProperties ? System.getProperty(key) : System.getenv(key);

    if (isBlank(value)) {
      throw new IllegalArgumentException(
          String.format("The environment variable ['%s'] is required but it has not been defined", key));
    }

    return value;
  }
}