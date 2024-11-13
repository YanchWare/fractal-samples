package com.yanchware.fractal.samples.environment.initialization;

import com.yanchware.fractal.samples.environment.initialization.configuration.Configuration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.samples.environment.initialization.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EnvironmentInitializationSample {
    public static void main(String[] args) throws InstantiatorException {
        var configuration = EnvVarConfiguration.getInstance();
        var automaton = Automaton.getInstance();
        automaton.instantiate(getFractalCloudEnvironment(automaton, configuration));
    }

    private static EnvironmentAggregate getFractalCloudEnvironment(Automaton automaton, Configuration configuration) {
        return automaton.getEnvironmentBuilder()
                .withId(new EnvironmentIdValue(
                        EnvironmentType.PERSONAL,
                        configuration.getEnvironmentOwnerId(),
                        "test-environment"))
                .withName("Test Environment")
                .withResourceGroup(configuration.getResourceGroupId())
                .withAwsCloudAgent(
                        AwsRegion.EU_WEST_1,
                        configuration.getAwsOrganizationId(),
                        configuration.getAwsAccountId())
//                .withAzureCloudAgent(
//                        AzureRegion.WEST_EUROPE,
//                        configuration.getTenantId(),
//                        configuration.getSubscriptionId())
                .build();
    }
}