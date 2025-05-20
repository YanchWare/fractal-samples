package com.yanchware.fractal.samples.environment.initialization.secrets.configuration;

import com.yanchware.fractal.sdk.domain.environment.Secret;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;

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
    public Collection<Secret> getAdditionalSecrets() {
        return List.of(
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

    @Override
    public Secret getSshPrivateKeySecret() {
        return new Secret(
                getVariableValue(Constants.DEPLOYER_SSH_KEY_SECRET_SHORT_NAME_ENV_VAR_KEY),
                getVariableValue(Constants.DEPLOYER_SSH_KEY_SECRET_DISPLAY_NAME_ENV_VAR_KEY),
                getVariableValue(Constants.DEPLOYER_SSH_KEY_SECRET_DESCRIPTION_ENV_VAR_KEY),
                getVariableValue(Constants.DEPLOYER_SSH_KEY_SECRET_VALUE_ENV_VAR_KEY));
    }

    @Override
    public Secret getSshPrivateKeyPassphraseSecret() {
        return new Secret(
                getVariableValue(Constants.DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_SHORT_NAME_ENV_VAR_KEY),
                getVariableValue(Constants.DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_DISPLAY_NAME_ENV_VAR_KEY),
                getVariableValue(Constants.DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_DESCRIPTION_ENV_VAR_KEY),
                getVariableValue(Constants.DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_VALUE_ENV_VAR_KEY));
    }

    @Override
    public String getGcpOrganizationId() {
        return getVariableValue(Constants.GCP_ORGANIZATION_ID_ENV_VAR_KEY);
    }

    @Override
    public String getGcpProjectId() {
        return getVariableValue(Constants.GCP_PROJECT_ID_ENV_VAR_KEY);
    }

    @Override
    public GcpRegion getGcpRegion() {
        return GcpRegion.fromString(getVariableValue(Constants.GCP_REGION_ENV_VAR_KEY));
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