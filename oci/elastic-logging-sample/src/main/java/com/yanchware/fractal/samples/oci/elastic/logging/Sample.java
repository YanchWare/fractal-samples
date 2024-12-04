package com.yanchware.fractal.samples.oci.elastic.logging;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.blueprint.FractalIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;
import com.yanchware.fractal.sdk.domain.livesystem.service.dtos.ProviderType;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.samples.oci.elastic.logging.components.OkeComponent.getOkeWithElasticLogging;

public class Sample {
  protected static final String LIVE_SYSTEM_NAME = "oci-elastic-logging-sample";
  protected static final OciRegion REGION = OciRegion.EU_MILAN_1;

  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(InstantiationWaitConfiguration.builder()
              .withWaitForInstantiation(true)
              .withTimeoutMinutes(120)
              .build()).build();
    
    // INSTANTIATION:
    var automaton = Automaton.getInstance();
    automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getFractalResourceGroupId().toString(), LIVE_SYSTEM_NAME))
        .withDescription("Elastic Logging in OKE sample")
        .withComponent(getOkeWithElasticLogging("oke-elastic-logging-1", configuration.getOciCompartment(), REGION))
        .withEnvironmentId(configuration.getFractalEnvironment(REGION).getManagementEnvironment().getId())
        .withFractalId(new FractalIdValue(configuration.getFractalResourceGroupId().toString(), LIVE_SYSTEM_NAME, "v1.0"))
        .withStandardProvider(ProviderType.OCI)
        .build();
  }
}