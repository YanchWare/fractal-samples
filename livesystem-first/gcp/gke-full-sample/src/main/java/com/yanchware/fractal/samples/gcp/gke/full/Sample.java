package com.yanchware.fractal.samples.gcp.gke.full;

import com.yanchware.fractal.samples.gcp.sharedconfig.SharedConfig;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.blueprint.FractalIdValue;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;
import com.yanchware.fractal.sdk.domain.livesystem.service.dtos.ProviderType;

import java.util.List;

import static com.yanchware.fractal.samples.gcp.gke.full.components.GkeComponent.getGke;

public class Sample {
  protected static final String LIVE_SYSTEM_NAME = "gcp-gke-full-sample";
    protected static final GcpRegion REGION = GcpRegion.EUROPE_WEST12;

  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();


    // INSTANTIATION:
    var automaton = Automaton.getInstance();
    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(
                    InstantiationWaitConfiguration.builder()
                            .withTimeoutMinutes(55)
                            .withWaitForInstantiation(true)
                            .build())
            .build();

    automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getFractalCloudResourceGroupId(), LIVE_SYSTEM_NAME))
        .withFractalId(new FractalIdValue(configuration.getFractalCloudResourceGroupId(), LIVE_SYSTEM_NAME, "v1.0"))
        .withDescription("GKE with full options sample")
        .withComponent(getGke("gke-1", REGION))
        .withStandardProvider(ProviderType.GCP)
        .withEnvironmentId(configuration.getFractalCloudEnvironment(REGION).getManagementEnvironment().getId())
        .build();
  }
}