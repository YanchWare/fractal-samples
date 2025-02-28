package com.yanchware.fractal.samples.environment.intialization.operational;

import com.yanchware.fractal.samples.environment.intialization.operational.configuration.Configuration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.*;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.samples.environment.intialization.operational.configuration.EnvVarConfiguration;

public class OperationalEnvironmentSample {
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
                        .withOperationalEnvironment(OperationalEnvironment.builder()
                                .withShortName(configuration.getOperationalEnvironmentShortName())
                                .withName(configuration.getOperationalEnvironmentName())
                                .withResourceGroup(configuration.getOperationalEnvironmentResourceGroup())
                                .withAzureSubscription(
                                        configuration.getOperationalEnvironmentAzureRegion(),
                                        configuration.getOperationalEnvironmentAzureSubscriptionId())
                                .withDefaultCiCdProfile(configuration.getOperationalEnvironmentDefaultCiCdProfile())
                                .withSecret(configuration.getOperationalEnvironmentSecret())
                                .build())
                        .build())
                .build();
    }
}