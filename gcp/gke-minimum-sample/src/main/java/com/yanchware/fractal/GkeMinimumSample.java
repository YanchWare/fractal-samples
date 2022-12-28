package com.yanchware.fractal;

import com.yanchware.fractal.gcp.gke.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.gcp.gke.sample.components.GkeComponent.getGke;

public class GkeMinimumSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = EnvVarConfiguration.getInstance();

    var env = Environment.builder()
        .withId(configuration.getProjectId())
        .withDisplayName(configuration.getEnvironmentDisplayName())
        .withParentId(configuration.getOrganizationId())
        .withParentType("organization")
        .build();

    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("GKE with minimum requirements sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getGke("gke-1"))
        .withEnvironment(env)
        .build();

    Automaton.instantiate(List.of(liveSystem));
  }
}