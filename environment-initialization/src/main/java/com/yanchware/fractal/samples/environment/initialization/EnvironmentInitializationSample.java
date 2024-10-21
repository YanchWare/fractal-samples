package com.yanchware.fractal.samples.environment.initialization;

import com.yanchware.fractal.samples.environment.initialization.configuration.Configuration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.samples.environment.initialization.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;

public class EnvironmentInitializationSample {
    public static void main(String[] args) throws InstantiatorException {
        var configuration = EnvVarConfiguration.getInstance();
        var automaton = Automaton.getInstance();
        automaton.instantiate(getFractalCloudEnvironment(automaton, configuration));
    }

    private static EnvironmentAggregate getFractalCloudEnvironment(Automaton automaton, Configuration configuration) throws InstantiatorException {
        var environment = automaton.getEnvironmentBuilder()
                .withId(new EnvironmentIdValue(
                        EnvironmentType.PERSONAL,
                        configuration.getEnvironmentOwnerId(),
                        configuration.getEnvironmentShortName()))
                .withName("Test Environment")
                .withResourceGroup(configuration.getResourceGroupId())
                .withAzureCloudAgent(
                        AzureRegion.WEST_EUROPE,
                        configuration.getTenantId(),
                        configuration.getSubscriptionId())
                .build();
        environment.createOrUpdate();
        return environment;
    }
}