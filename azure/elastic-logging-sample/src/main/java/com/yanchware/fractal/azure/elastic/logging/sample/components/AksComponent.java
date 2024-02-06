package com.yanchware.fractal.azure.elastic.logging.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSElasticLogging;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureNodePool;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureMachineType.STANDARD_B2S;

public class AksComponent {

  public static AzureKubernetesService getAks(String id, AzureResourceGroup resourceGroup) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withRegion(resourceGroup.getRegion())
        .withResourceGroup(resourceGroup)
        .withNodePools(getNodePools())
        .withLogging(getElasticLoggingExample())
        .build();
  }

  public static Collection<? extends AzureNodePool> getNodePools() {
    return List.of(
        AzureNodePool.builder()
            .withName("linuxdynamic")
            .withMachineType(STANDARD_B2S)
            .build()
    );
  }

  public static CaaSElasticLogging getElasticLoggingExample() {
    return CaaSElasticLogging.builder()
        .withId("elastic-logging")
        .withVersion("2.5.0")
        .withNamespace("elastic")
        .withKibana(true)
        .withAPM(true)
        .withElasticVersion("8.5")
        .withInstances(1)
        .withStorage("10Gi")
        .withStorageClassName("standard")
        .withMemory(1)
        .withCpu(1)
        .build();
  }

}
