package com.yanchware.fractal;

import com.yanchware.fractal.azure.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.azure.sample.components.AzServiceBus.getServiceBus;

public class ServiceBusFullSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = EnvVarConfiguration.getInstance();

    var env = Environment.builder()
        .withEnvironmentType(EnvironmentType.fromString(configuration.getEnvironmentType()))
        .withId(configuration.getEnvironmentId())
        .withOwnerId(configuration.getEnvironmentOwnerId())
        .build();


    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Service Bus with full options sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getServiceBus())
        .withEnvironment(env)
        .build();

    Automaton.instantiate(List.of(liveSystem));
  }
}