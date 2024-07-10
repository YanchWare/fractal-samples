package com.yanchware.fractal.samples.environment.initialization.configuration;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class EnvVarConfiguration implements Configuration {

  private boolean readFromProperties;

  public EnvVarConfiguration() {
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
  public UUID getTenantId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_TENANT_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getSubscriptionId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_SUBSCRIPTION_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getEnvironmentOwnerId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getResourceGroupId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY));
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