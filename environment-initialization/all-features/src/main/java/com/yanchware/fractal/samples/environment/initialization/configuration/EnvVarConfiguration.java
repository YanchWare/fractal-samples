package com.yanchware.fractal.samples.environment.initialization.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;
import com.yanchware.fractal.sdk.domain.environment.Secret;

import java.util.Collection;
import java.util.List;
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
  public UUID getAzureTenantId() {
    return UUID.fromString(getVariableValue(Constants.AZURE_TENANT_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getAzureSubscriptionId() {
    return UUID.fromString(getVariableValue(Constants.AZURE_SUBSCRIPTION_ID_ENV_VAR_KEY));
  }

  @Override
  public String getAwsOrganizationId() {
    return getVariableValue(Constants.AWS_ORGANIZATION_ID_ENV_VAR_KEY);
  }

  @Override
  public String getAwsAccountId() {
    return getVariableValue(Constants.AWS_ACCOUNT_ID_ENV_VAR_KEY);
  }

  @Override
  public UUID getEnvironmentOwnerId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY));
  }

  @Override
  public UUID getResourceGroupId() {
    return UUID.fromString(getVariableValue(Constants.FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY));
  }

  @Override
  public CiCdProfile getDefaultCiCdProfile() {
    return new CiCdProfile(
            getVariableValue(Constants.DEFAULT_CI_CD_PROFILE_SHORT_NAME_ENV_VAR_KEY),
            getVariableValue(Constants.DEFAULT_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR_KEY),
            getVariableValue(Constants.DEFAULT_CI_CD_PROFILE_DESCRIPTION_ENV_VAR_KEY),
            getVariableValue(Constants.DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR_KEY),
            getVariableValue(Constants.DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR_KEY));
  }

  @Override
  public Collection<CiCdProfile> getAdditionalCiCdProfile() {
    return List.of(
            new CiCdProfile(
                    getVariableValue(Constants.SECOND_CI_CD_PROFILE_SHORT_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_CI_CD_PROFILE_DESCRIPTION_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR_KEY)),
            new CiCdProfile(
                    getVariableValue(Constants.THIRD_CI_CD_PROFILE_SHORT_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_CI_CD_PROFILE_DESCRIPTION_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR_KEY))
    );
  }

  @Override
  public Collection<Secret> getEnvironmentSecrets() {
    return List.of(
            new Secret(
                    getVariableValue(Constants.FIRST_SECRET_SHORT_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.FIRST_SECRET_DISPLAY_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.FIRST_SECRET_DESCRIPTION_ENV_VAR_KEY),
                    getVariableValue(Constants.FIRST_SECRET_VALUE_ENV_VAR_KEY)),
            new Secret(
                    getVariableValue(Constants.SECOND_SECRET_SHORT_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_SECRET_DISPLAY_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_SECRET_DESCRIPTION_ENV_VAR_KEY),
                    getVariableValue(Constants.SECOND_SECRET_VALUE_ENV_VAR_KEY)),
            new Secret(
                    getVariableValue(Constants.THIRD_SECRET_SHORT_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_SECRET_DISPLAY_NAME_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_SECRET_DESCRIPTION_ENV_VAR_KEY),
                    getVariableValue(Constants.THIRD_SECRET_VALUE_ENV_VAR_KEY))
    );
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