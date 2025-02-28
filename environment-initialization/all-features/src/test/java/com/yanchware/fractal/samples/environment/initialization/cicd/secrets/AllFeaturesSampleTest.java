package com.yanchware.fractal.samples.environment.initialization.cicd.secrets;

import com.yanchware.fractal.samples.environment.initialization.cicd.secrets.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.*;
import com.yanchware.fractal.sdk.domain.environment.aws.AwsCloudAgent;
import com.yanchware.fractal.sdk.domain.environment.azure.AzureCloudAgent;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.service.dtos.ProviderType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.yanchware.fractal.samples.environment.initialization.cicd.secrets.AllFeaturesSample.getFractalCloudEnvironment;
import static com.yanchware.fractal.samples.environment.initialization.cicd.secrets.configuration.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AllFeaturesSampleTest extends BaseTest {
    @Test
    void should_createManagementEnvironment_withValidData() throws InstantiatorException {
        // Arrange
        var expectedDefaultCiCdProfile = new CiCdProfile(
                DEFAULT_CI_CD_PROFILE_SHORT_NAME_ENV_VAR.value(),
                DEFAULT_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR.value(),
                DEFAULT_CI_CD_PROFILE_DESCRIPTION_ENV_VAR.value(),
                DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR.value(),
                DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR.value());

        var expectedSecondCiCdProfile = new CiCdProfile(
                SECOND_CI_CD_PROFILE_SHORT_NAME_ENV_VAR.value(),
                SECOND_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR.value(),
                SECOND_CI_CD_PROFILE_DESCRIPTION_ENV_VAR.value(),
                SECOND_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR.value(),
                SECOND_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR.value());

        var expectedThirdtCiCdProfile = new CiCdProfile(
                THIRD_CI_CD_PROFILE_SHORT_NAME_ENV_VAR.value(),
                THIRD_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR.value(),
                THIRD_CI_CD_PROFILE_DESCRIPTION_ENV_VAR.value(),
                THIRD_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR.value(),
                THIRD_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR.value());

        var expectedFirstSecret = new Secret(
                FIRST_SECRET_SHORT_NAME_ENV_VAR.value(),
                FIRST_SECRET_DISPLAY_NAME_ENV_VAR.value(),
                FIRST_SECRET_DESCRIPTION_ENV_VAR.value(),
                FIRST_SECRET_VALUE_ENV_VAR.value());

        var expectedSecondSecret = new Secret(
                SECOND_SECRET_SHORT_NAME_ENV_VAR.value(),
                SECOND_SECRET_DISPLAY_NAME_ENV_VAR.value(),
                SECOND_SECRET_DESCRIPTION_ENV_VAR.value(),
                SECOND_SECRET_VALUE_ENV_VAR.value());

        var expectedThirdSecret = new Secret(
                THIRD_SECRET_SHORT_NAME_ENV_VAR.value(),
                THIRD_SECRET_DISPLAY_NAME_ENV_VAR.value(),
                THIRD_SECRET_DESCRIPTION_ENV_VAR.value(),
                THIRD_SECRET_VALUE_ENV_VAR.value());


        var configuration = EnvVarConfiguration.getInstance(true);

        Automaton automaton = Automaton.getInstance();

        // Act
        EnvironmentAggregate environmentAggregate = getFractalCloudEnvironment(automaton, configuration);

        // Assert
        assertThat(environmentAggregate).isNotNull();
        assertThat(environmentAggregate.getManagementEnvironment()).isNotNull();

        var managementEnvironment = environmentAggregate.getManagementEnvironment();

        assertThat(managementEnvironment.getId()).isEqualTo(new EnvironmentIdValue(
                EnvironmentType.fromString(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR.value()),
                UUID.fromString(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR.value()),
                MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR.value()
        ));
        assertThat(managementEnvironment.getName()).isEqualTo(MANAGEMENT_ENVIRONMENT_NAME_ENV.value());
        assertThat(managementEnvironment.getResourceGroups())
                .containsExactly(UUID.fromString(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR.value()));

        // Assert AWS Cloud Agent
        var awsAgent = managementEnvironment.getCloudAgentByProviderType().get(ProviderType.AWS);
        assertThat(awsAgent).isInstanceOf(AwsCloudAgent.class);
        assertThat(((AwsCloudAgent) awsAgent).getRegion().toString()).isEqualTo(AWS_REGION_ENV_VAR.value());
        assertThat(((AwsCloudAgent) awsAgent).getOrganizationId()).isEqualTo(AWS_ORGANIZATION_ID_ENV_VAR.value());
        assertThat(((AwsCloudAgent) awsAgent).getAccountId()).isEqualTo(AWS_ACCOUNT_ID_ENV_VAR.value());

        // Assert Azure Cloud Agent
        var azureAgent = managementEnvironment.getCloudAgentByProviderType().get(ProviderType.AZURE);
        assertThat(azureAgent).isInstanceOf(AzureCloudAgent.class);
        assertThat(((AzureCloudAgent) azureAgent).getRegion().toString()).isEqualTo(AZURE_REGION_ENV_VAR.value());
        assertThat(((AzureCloudAgent) azureAgent).getTenantId()).isEqualTo(UUID.fromString(AZURE_TENANT_ID_ENV_VAR.value()));
        assertThat(((AzureCloudAgent) azureAgent).getSubscriptionId()).isEqualTo(UUID.fromString(AZURE_SUBSCRIPTION_ID_ENV_VAR.value()));

        assertThat(managementEnvironment.getDefaultCiCdProfile()).isEqualTo(expectedDefaultCiCdProfile);
        assertThat(managementEnvironment.getCiCdProfiles()).isNotNull();
        assertThat(managementEnvironment.getCiCdProfiles()).size().isEqualTo(2);
        assertThat(managementEnvironment.getCiCdProfiles())
                .containsExactly(
                        expectedSecondCiCdProfile,
                        expectedThirdtCiCdProfile
                );

        assertThat(managementEnvironment.getSecrets()).isNotNull();
        var environmentSecrets = managementEnvironment.getSecrets();
        assertThat(environmentSecrets).size().isEqualTo(3);
        assertThat(environmentSecrets)
                .containsExactly(
                        expectedFirstSecret,
                        expectedSecondSecret,
                        expectedThirdSecret
                );
    }

    @Test
    void should_throwException_when_environmentTypeIsInvalid() throws InstantiatorException {
        // Arrange
        var configuration = EnvVarConfiguration.getInstance(true);
        Automaton automaton = Automaton.getInstance();
        System.setProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR.key(), "INVALID_TYPE");

        // Act & Assert
        assertThatThrownBy(() -> getFractalCloudEnvironment(automaton, configuration))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid environment type: 'INVALID_TYPE'");
    }

    @Test
    void should_throwException_when_environmentOwnerIsInvalid() throws InstantiatorException {
        // Arrange
        var configuration = EnvVarConfiguration.getInstance(true);
        Automaton automaton = Automaton.getInstance();
        System.setProperty(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR.key(), "INVALID_OWNER");

        // Act & Assert
        assertThatThrownBy(() -> getFractalCloudEnvironment(automaton, configuration))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string: INVALID_OWNER");
    }

    @Test
    void should_throwException_when_environmentShortNameIsInvalid() throws InstantiatorException {
        // Arrange
        var configuration = EnvVarConfiguration.getInstance(true);
        Automaton automaton = Automaton.getInstance();
        System.setProperty(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR.key(), "INVALID_SHORT_NAME");

        // Act & Assert
        assertThatThrownBy(() -> getFractalCloudEnvironment(automaton, configuration))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Environment ShortName must only contain lowercase letters, numbers, and dashes"); // Or a more specific error message
    }

    @Test
    void should_throwException_when_AzureSubscriptionIdIsInvalid() throws InstantiatorException {
        // Arrange
        var configuration = EnvVarConfiguration.getInstance(true);
        Automaton automaton = Automaton.getInstance();
        System.setProperty(AZURE_SUBSCRIPTION_ID_ENV_VAR.key(), "INVALID_SUBSCRIPTION_ID");

        // Act & Assert
        assertThatThrownBy(() -> getFractalCloudEnvironment(automaton, configuration))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string");
    }

    @Test
    void should_throwException_when_AzureTenantIdIsInvalid() throws InstantiatorException {
        // Arrange
        var configuration = EnvVarConfiguration.getInstance(true);
        Automaton automaton = Automaton.getInstance();
        System.setProperty(AZURE_TENANT_ID_ENV_VAR.key(), "INVALID_TENANT_ID");

        // Act & Assert
        assertThatThrownBy(() -> getFractalCloudEnvironment(automaton, configuration))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid UUID string");
    }
}