package com.yanchware.fractal.pocs.migration.infrastructure.configuration;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class EnvVarInfrastructureConfiguration implements InfrastructureConfiguration {

  private boolean readFromProperties;

  public EnvVarInfrastructureConfiguration() {
    new EnvVarInfrastructureConfiguration(false);
  }

  public EnvVarInfrastructureConfiguration(boolean readFromProperties) {
    this.readFromProperties = readFromProperties;
  }

  public static InfrastructureConfiguration getInstance() {
    return new EnvVarInfrastructureConfiguration();
  }

  public static InfrastructureConfiguration getInstance(boolean readFromProperties) {
    return new EnvVarInfrastructureConfiguration(readFromProperties);
  }

  @Override
  public UUID getEnvironmentOwnerId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getTenantId() {
    return UUID.fromString(getVariableValue(Constants.TENANT_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getSubscriptionId() {
    return UUID.fromString(getVariableValue(Constants.SUBSCRIPTION_ID_ENV_VAR_KEY));
  }


  @Override
  public String getEnvironmentShortName() {
    return getVariableValue(Constants.FRACTAL_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY);
  }

  @Override
  public String getFractalCloudResourceGroupId() {
    return getVariableValue(Constants.FRACTAL_CLOUD_RESOURCE_GROUP_ID_ENV_VAR_KEY);
  }

  @Override
  public String getEnvironmentName() {
    return getVariableValue(Constants.FRACTAL_ENVIRONMENT_NAME_ENV_VAR_KEY);
  }

  protected String getVariableValue(String key) {

    var value = readFromProperties ? System.getProperty(key) : System.getenv(key);

    if (isBlank(value)) {
      throw new IllegalArgumentException(
              String.format("The environment variable ['%s'] is required but it has not been defined", key));
    }

      return value;
  }
}