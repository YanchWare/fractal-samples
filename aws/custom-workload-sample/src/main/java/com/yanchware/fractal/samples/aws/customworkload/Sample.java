package com.yanchware.fractal.samples.aws.customworkload;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.blueprint.FractalIdValue;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;
import com.yanchware.fractal.sdk.domain.livesystem.service.dtos.ProviderType;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.samples.aws.customworkload.components.EksComponent.getEksWithCustomWorkload;

public class Sample {
  protected static final String LIVE_SYSTEM_NAME = "aws-custom-workload-sample";
  protected static final AwsRegion REGION = AwsRegion.EU_CENTRAL_1;

  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(
                    InstantiationWaitConfiguration.builder()
                            .withTimeoutMinutes(55)
                            .withWaitForInstantiation(true)
                            .build())
            .build();
    // INSTANTIATION:
    var automaton = Automaton.getInstance();
    automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getFractalCloudResourceGroupId(), LIVE_SYSTEM_NAME))
        .withDescription("Custom Workload in EKS sample")
        .withComponent(getEksWithCustomWorkload("eks-custom-workload-1", REGION))
        .withFractalId(new FractalIdValue(configuration.getFractalCloudResourceGroupId(), LIVE_SYSTEM_NAME, "v1.0"))
        .withEnvironmentId(configuration.getEnvironment(REGION).getManagementEnvironment().getId())
        .withStandardProvider(ProviderType.AWS)
        .build();
  }
}