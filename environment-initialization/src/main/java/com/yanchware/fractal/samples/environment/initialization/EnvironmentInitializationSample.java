package com.yanchware.fractal.samples.environment.initialization;

import com.yanchware.fractal.samples.environment.initialization.configuration.Configuration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.samples.environment.initialization.configuration.EnvVarConfiguration;

public class EnvironmentInitializationSample {
    private static Environment getFractalCloudEnvironment(Configuration configuration) {
        return Environment.builder()
                .withEnvironmentType(EnvironmentType.PERSONAL)
                .withOwnerId(configuration.getEnvironmentOwnerId())
                .withShortName("test-environment")
                .withName("Test Environment")
                .withResourceGroup(configuration.getResourceGroupId())
                .withRegion(AzureRegion.WEST_EUROPE)
                .withTenantId(configuration.getTenantId())
                .withSubscriptionId(configuration.getSubscriptionId())
                .build();
    }

    public static void main(String[] args) throws InstantiatorException {
        var configuration = EnvVarConfiguration.getInstance();
        Automaton.instantiate(getFractalCloudEnvironment(configuration));
    }
}