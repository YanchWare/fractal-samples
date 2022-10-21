package com.yanchware.fractal.sdk.sample;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.sample.configuration.EnvVarConfiguration;

import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;
import static com.yanchware.fractal.sdk.sample.components.AmbassadorConfiguration.getAmbassador;
import static com.yanchware.fractal.sdk.sample.components.HelloWorldWorkloadConfiguration.getHelloWorldWorkloadConfiguration;
import static com.yanchware.fractal.sdk.sample.components.PostgresConfiguration.getPostgresDbms;
import static com.yanchware.fractal.sdk.sample.configuration.EnvironmentConfiguration.getEnvironment;

public class SdkSample {

  public static void main(String[] args) throws InstantiatorException {
    var configuration = EnvVarConfiguration.getInstance();
    
    var aks = AzureKubernetesService.builder()
        .withId("aks-cluster")
        .withDisplayName("AKS cluster")
        .withRegion(EUROPE_WEST)
        .withNodePool(AzureNodePool.builder()
            .withName("linux")
            .withMachineType(STANDARD_B2S)
            .build())
        .withAPIGateway(getAmbassador())
        .withK8sWorkload(getHelloWorldWorkloadConfiguration())
        .build();

    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Microservice with DB and Ambassador API Gateway")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponents(List.of(aks, getPostgresDbms()))
        .withEnvironment(getEnvironment(configuration))
        .build();

    Automaton.instantiate(List.of(liveSystem));
  }
}
