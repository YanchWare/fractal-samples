package com.yanchware.fractal;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfig;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;

import java.util.List;

import static com.yanchware.fractal.gcp.gke.elastic.logging.sample.components.GkeComponent.getGke;

public class ElasticLoggingSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    // INSTANTIATION:
    var automaton = Automaton.getInstance();
    automaton.instantiate(List.of(getLiveSystem(automaton, configuration)));
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getResourceGroupId().toString(), configuration.getLiveSystemName()))
        .withDescription("Elastic Logging in GKE sample")
        .withComponent(getGke("gke-1", configuration))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}