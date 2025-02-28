package com.yanchware.fractal.samples.environment.intialization.management.configuration;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.yanchware.fractal.samples.environment.intialization.management.configuration.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnvVarConfigurationTest {

    @Test
    void should_getAwsAccountId_fromEnvVar() {
        // Arrange
        String expectedAccountId = "123456789012";
        System.setProperty(AWS_ACCOUNT_ID_ENV_VAR_KEY, expectedAccountId);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        String accountId = config.getAwsAccountId();

        // Assert
        assertThat(accountId).isEqualTo(expectedAccountId);
    }

    @Test
    void should_throwException_when_awsAccountIdIsMissing() {
        // Arrange
        System.clearProperty(AWS_ACCOUNT_ID_ENV_VAR_KEY);
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getAwsAccountId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("The environment variable ['%s'] is required but it has not been defined", AWS_ACCOUNT_ID_ENV_VAR_KEY));
    }

    @Test
    void should_getAwsOrganizationId_fromEnvVar() {
        // Arrange
        String expectedOrganizationId = "my-org-id";
        System.setProperty(AWS_ORGANIZATION_ID_ENV_VAR_KEY, expectedOrganizationId);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        String organizationId = config.getAwsOrganizationId();

        // Assert
        assertThat(organizationId).isEqualTo(expectedOrganizationId);
    }

    @Test
    void should_throwException_when_awsOrganizationIdIsMissing() {
        // Arrange
        System.clearProperty(AWS_ORGANIZATION_ID_ENV_VAR_KEY);
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getAwsOrganizationId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("The environment variable ['%s'] is required but it has not been defined", AWS_ORGANIZATION_ID_ENV_VAR_KEY));
    }

    @Test
    void should_getAwsRegion_fromEnvVar() {
        // Arrange
        System.setProperty(AWS_REGION_ENV_VAR_KEY, "eu-west-1");
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var awsRegion = config.getAwsRegion();

        // Assert
        assertThat(awsRegion).isEqualTo(AwsRegion.EU_WEST_1);
    }

    @Test
    void should_getAzureRegion_fromEnvVar() {
        // Arrange
        System.setProperty(AZURE_REGION_ENV_VAR_KEY, "westeurope");
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var azureRegion = config.getAzureRegion();

        // Assert
        assertThat(azureRegion).isEqualTo(AzureRegion.WEST_EUROPE);
    }

    @Test
    void should_getAzureSubscriptionId_fromEnvVar() {
        // Arrange
        String expectedSubscriptionId = UUID.randomUUID().toString();
        System.setProperty(AZURE_SUBSCRIPTION_ID_ENV_VAR_KEY, expectedSubscriptionId);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var subscriptionId = config.getAzureSubscriptionId();

        // Assert
        assertThat(subscriptionId).isEqualTo(UUID.fromString(expectedSubscriptionId));
    }

    @Test
    void should_throwException_when_azureSubscriptionIdIsInvalid() {
        // Arrange
        System.setProperty(AZURE_SUBSCRIPTION_ID_ENV_VAR_KEY, "invalid-uuid");
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getAzureSubscriptionId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string");
    }

    @Test
    void should_getAzureTenantId_fromEnvVar() {
        // Arrange
        String expectedTenantId = UUID.randomUUID().toString();
        System.setProperty(AZURE_TENANT_ID_ENV_VAR_KEY, expectedTenantId);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var tenantId = config.getAzureTenantId();

        // Assert
        assertThat(tenantId).isEqualTo(UUID.fromString(expectedTenantId));
    }

    @Test
    void should_throwException_when_azureTenantIdIsInvalid() {
        // Arrange
        System.setProperty(AZURE_TENANT_ID_ENV_VAR_KEY, "invalid-uuid");
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getAzureTenantId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string");
    }

    @Test
    void should_getEnvironmentOwnerId_fromEnvVar() {
        // Arrange
        String expectedOwnerId = UUID.randomUUID().toString();
        System.setProperty(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY, expectedOwnerId);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var ownerId = config.getEnvironmentOwnerId();

        // Assert
        assertThat(ownerId).isEqualTo(UUID.fromString(expectedOwnerId));
    }

    @Test
    void should_throwException_when_environmentOwnerIdIsInvalid() {
        // Arrange
        System.setProperty(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY, "invalid-uuid");
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getEnvironmentOwnerId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string");
    }

    @Test
    void should_getManagementEnvironmentId_fromEnvVars() {
        // Arrange
        System.setProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY, "Personal");
        System.setProperty(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY, UUID.randomUUID().toString());
        System.setProperty(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY, "test-env");
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var environmentId = config.getManagementEnvironmentId();

        // Assert
        assertThat(environmentId).isNotNull();
        assertThat(environmentId).isEqualTo(new EnvironmentIdValue(
                EnvironmentType.fromString(System.getProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY)),
                UUID.fromString(System.getProperty(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY)),
                System.getProperty(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY)
        ));
    }

    @Test
    void should_getManagementEnvironmentName_fromEnvVar() {
        // Arrange
        String expectedName = "Test Environment";
        System.setProperty(MANAGEMENT_ENVIRONMENT_NAME_ENV_VAR_KEY, expectedName);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        String name = config.getManagementEnvironmentName();

        // Assert
        assertThat(name).isEqualTo(expectedName);
    }

    @Test
    void should_throwException_when_managementEnvironmentNameIsMissing() {
        // Arrange
        System.clearProperty(MANAGEMENT_ENVIRONMENT_NAME_ENV_VAR_KEY);
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getManagementEnvironmentName)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("The environment variable ['%s'] is required but it has not been defined", MANAGEMENT_ENVIRONMENT_NAME_ENV_VAR_KEY));
    }
    
    @Test
    void should_getManagementEnvironmentShortName_fromEnvVar() {
        // Arrange
        String expectedShortName = "test-environment";
        System.setProperty(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY, expectedShortName);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        String shortName = config.getManagementEnvironmentShortName();

        // Assert
        assertThat(shortName).isEqualTo(expectedShortName);
    }

    @Test
    void should_throwException_when_managementEnvironmentShortNameIsMissing() {
        // Arrange
        System.clearProperty(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY);
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getManagementEnvironmentShortName)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("The environment variable ['%s'] is required but it has not been defined", MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY));
    }

    @Test
    void should_getManagementEnvironmentType_fromEnvVar() {
        // Arrange
        String expectedType = "Personal";
        System.setProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY, expectedType);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var environmentType = config.getManagementEnvironmentType();

        // Assert
        assertThat(environmentType).isEqualTo(EnvironmentType.fromString(expectedType));
    }

    @Test
    void should_throwException_when_environmentTypeIsInvalid() {
        // Arrange
        System.setProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY, "invalid-type");
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getManagementEnvironmentType)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid environment type: 'invalid-type'");
    }

    @Test
    void should_throwException_when_managementEnvironmentTypeIsMissing() {
        // Arrange
        System.clearProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY);
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getManagementEnvironmentType)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("The environment variable ['%s'] is required but it has not been defined", MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY));
    }

    @Test
    void should_getResourceGroupId_fromEnvVar() {
        // Arrange
        String expected = UUID.randomUUID().toString();
        System.setProperty(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY, expected);
        var config = EnvVarConfiguration.getInstance(true);

        // Act
        var resourceGroupId = config.getResourceGroupId();

        // Assert
        assertThat(resourceGroupId).isEqualTo(UUID.fromString(expected));
    }

    @Test
    void should_throwException_when_resourceGroupIdIsInvalid() {
        // Arrange
        System.setProperty(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY, "invalid-uuid");
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getResourceGroupId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string");
    }

    @Test
    void should_throwException_when_resourceGroupIdIsMissing() {
        // Arrange
        System.clearProperty(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY);
        var config = EnvVarConfiguration.getInstance(true);

        // Act & Assert
        assertThatThrownBy(config::getResourceGroupId)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("The environment variable ['%s'] is required but it has not been defined", FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY));
    }
}