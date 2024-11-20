package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.oci.oke.minimum.sample.components.OkeComponent.getOke;

public class OkeMinimumSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(
                    InstantiationWaitConfiguration.builder()
                            .withWaitForInstantiation(true)
                            .withTimeoutMinutes(120).build())
            .build();

    // INSTANTIATION:
    var automaton = Automaton.getInstance();
    automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getResourceGroupId().toString(), configuration.getLiveSystemName()))
        .withDescription("OKE with full options sample")
        .withComponent(getOke("oke-minimum-1", configuration.getCompartment(), configuration.getOciRegion()))
        .withEnvironmentId(configuration.getEnvironment().getManagementEnvironment().getId())
        .build();
  }
}