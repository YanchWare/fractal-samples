package com.yanchware.fractal;

import com.yanchware.fractal.azure.ambassador.sample.configuration.Configuration;
import com.yanchware.fractal.azure.ambassador.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.azure.ambassador.sample.components.AksComponent.getAks;

public class AmbassadorSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = EnvVarConfiguration.getInstance();
    
    // INSTANTIATION:
    Automaton.instantiate(List.of(getLiveSystem(configuration)));
  }
  public static LiveSystem getLiveSystem(Configuration configuration) {
    var env = Environment.builder()
        .withEnvironmentType(EnvironmentType.fromString(configuration.getEnvironmentType()))
        .withId(configuration.getEnvironmentId())
        .withOwnerId(configuration.getEnvironmentOwnerId())
        .build();

    
    return LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Fractal demo")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getAks("aks-ambassador-1"))
        .withEnvironment(env)
        .build();
  }
}