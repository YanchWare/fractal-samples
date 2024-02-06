package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.azure.customworkload.sample.components.AksComponent.getAksWithCustomWorkload;

public class CustomWorkloadSample {
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
        .withDescription("Custom Workload in AKS sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getAksWithCustomWorkload("aks-custom-workload-1", configuration.getAzureResourceGroup()))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}