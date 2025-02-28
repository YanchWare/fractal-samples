package com.yanchware.fractal.samples.environment.initialization.cicd.secrets;

import com.yanchware.fractal.samples.environment.initialization.cicd.secrets.configuration.Configuration;
import com.yanchware.fractal.samples.environment.initialization.cicd.secrets.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.ManagementEnvironment;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

public class AllFeaturesSample {
    public static void main(String[] args) throws InstantiatorException {
        var configuration = EnvVarConfiguration.getInstance();
        var automaton = Automaton.getInstance();
        automaton.instantiate(getFractalCloudEnvironment(automaton, configuration));
    }

    static EnvironmentAggregate getFractalCloudEnvironment(Automaton automaton, Configuration configuration) {
        return automaton.getEnvironmentBuilder()
                .withManagementEnvironment(ManagementEnvironment.builder()
                        .withId(configuration.getManagementEnvironmentId())
                        .withName(configuration.getManagementEnvironmentName())
                        .withResourceGroup(configuration.getResourceGroupId())
                        .withAwsCloudAgent(
                                configuration.getAwsRegion(),
                                configuration.getAwsOrganizationId(),
                                configuration.getAwsAccountId())
                        .withAzureCloudAgent(
                                configuration.getAzureRegion(),
                                configuration.getAzureTenantId(),
                                configuration.getAzureSubscriptionId())
                        .withDefaultCiCdProfile(configuration.getDefaultCiCdProfile())//Default profile must be added in order to be able to use additional profiles
                        .withCiCdProfiles(configuration.getAdditionalCiCdProfiles())
                        .withSecrets(configuration.getEnvironmentSecrets())
                        .build())
                .build();
    }
}