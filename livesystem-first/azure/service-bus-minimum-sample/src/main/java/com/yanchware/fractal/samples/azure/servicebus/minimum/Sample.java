package com.yanchware.fractal.samples.azure.servicebus.minimum;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.blueprint.FractalIdValue;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.service.dtos.ProviderType;
import com.yanchware.fractal.samples.azure.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.samples.azure.servicebus.minimum.components.AzServiceBus.getServiceBus;

public class Sample {
  protected static final String LIVE_SYSTEM_NAME = "azure-servicebus-minimum-sample";
  protected static final AzureRegion REGION = AzureRegion.WEST_EUROPE;
  protected static final AzureResourceGroup RESOURCE_GROUP = AzureResourceGroup.builder()
          .withName("rg-samples")
          .withRegion(REGION)
          .withTag("Purpose", "Samples")
          .build();

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
        .withDescription("Service Bus with minimum requirements sample")
        .withComponent(getServiceBus(RESOURCE_GROUP))
        .withFractalId(new FractalIdValue(configuration.getFractalResourceGroupId().toString(), LIVE_SYSTEM_NAME, "v1.0"))
        .withStandardProvider(ProviderType.AZURE)
        .withEnvironmentId(configuration.getFractalEnvironment(REGION).getManagementEnvironment().getId())
        .build();
  }
}