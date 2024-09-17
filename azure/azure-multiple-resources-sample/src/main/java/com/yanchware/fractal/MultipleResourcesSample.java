package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.components.AzServiceBus.getServiceBus;
import static com.yanchware.fractal.components.WebAppComponent.getDotnetWebAppComponent;

public class MultipleResourcesSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(InstantiationWaitConfiguration.builder()
              .withWaitForInstantiation(true)
              .withTimeoutMinutes(120)
              .build()).build();

    // LIVE-SYSTEM INSTANTIATION:
    var automaton = Automaton.getInstance();
automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {

    var resourceGroup = configuration.getAzureResourceGroup();
    var serviceBusComponent =  getServiceBus("sb-minimum-sample", resourceGroup);
    
    // LIVE-SYSTEM DEFINITION:
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getResourceGroupId().toString(), configuration.getLiveSystemName()))
        .withDescription("CDN sample")
        .withComponents(List.of(
            serviceBusComponent,
            getDotnetWebAppComponent("app-dotnet-fractal-cloud-demo", serviceBusComponent.getId(), resourceGroup)))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}