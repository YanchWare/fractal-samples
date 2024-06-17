package com.yanchware.fractal;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfig;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.gcp.gke.elastic.datastore.sample.components.GkeComponent.getGke;

public class ElasticDatastoreSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();
    
    // INSTANTIATION:
    Automaton.instantiate(List.of(getLiveSystem(configuration)));
  }

  public static LiveSystem getLiveSystem(SharedConfig configuration) {
    return LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Elastic Logging in GKE sample")
        .withResourceGroupId(configuration.getResourceGroupId().toString())
        .withComponent(getGke("gke-1", configuration))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}