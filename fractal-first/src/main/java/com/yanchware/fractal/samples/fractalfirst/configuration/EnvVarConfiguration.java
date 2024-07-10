package com.yanchware.fractal.samples.fractalfirst.configuration;

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
  public UUID getEnvironmentOwnerId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY));
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
  public String getHelloWorldCustomWorkloadRepositoryName() {
    return getVariableValue(Constants.HELLO_WORLD_CUSTOM_WORKLOAD_REPOSITORY_NAME_ENV_VAR_KEY);
  }

  @Override
  public String getHelloWorldCustomWorkloadGitUri() {
    return getVariableValue(Constants.HELLO_WORLD_CUSTOM_WORKLOAD_GIT_URI_ENV_VAR_KEY);
  }

  @Override
  public String getFractalDeployerSshKeySecretValue() {
    return getVariableValue(Constants.FRACTAL_DEPLOYER_SSH_KEY_ENV_VAR_KEY);
  }

  @Override
  public String getFractalDeployerSshKeyPassphraseSecretValue() {
    return getVariableValue(Constants.FRACTAL_DEPLOYER_SSH_KEY_PASSPHRASE_ENV_VAR_KEY);
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