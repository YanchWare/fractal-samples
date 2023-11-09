package com.yanchware.fractal;

import com.yanchware.fractal.azure.customworkload.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.azure.customworkload.sample.components.AksComponent.getAksWithCustomWorkload;

public class CustomWorkloadSample {
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
        .withDescription("Custom Workload in AKS sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getAksWithCustomWorkload("aks-1"))
        .withEnvironment(env)
        .build();

    Automaton.instantiate(List.of(liveSystem));
  }
}