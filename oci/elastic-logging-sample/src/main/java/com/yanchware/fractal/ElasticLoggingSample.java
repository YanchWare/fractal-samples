package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.oci.elastic.logging.sample.components.OkeComponent.getOkeWithElasticLogging;

public class ElasticLoggingSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = new InstantiationConfiguration() {{
      setWaitConfiguration(new InstantiationWaitConfiguration() {{
        setWaitForInstantiation(true);
        setTimeoutMinutes(120);
      }});
    }};
    
    // INSTANTIATION:
    Automaton.instantiate(List.of(getLiveSystem(configuration)), instantiationConfig);
  }

  public static LiveSystem getLiveSystem(SharedConfig configuration) {
    return LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Elastic Logging in AKS sample")
        .withResourceGroupId(configuration.getResourceGroupId().toString())
        .withComponent(getOkeWithElasticLogging("aks-elastic-logging-1", configuration.getCompartment(), configuration.getOciRegion()))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}