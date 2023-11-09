package com.yanchware.fractal;

import com.yanchware.fractal.azure.aks.full.sample.configuration.Configuration;
import com.yanchware.fractal.azure.aks.full.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.azure.aks.full.sample.components.AksComponent.getAks;

public class AksFullSample {
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
        .withDescription("AKS with full options sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getAks("aks-full-1"))
        .withEnvironment(env)
        .build();
  }
}