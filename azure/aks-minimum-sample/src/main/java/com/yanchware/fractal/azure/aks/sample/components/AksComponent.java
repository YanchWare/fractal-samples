package com.yanchware.fractal.azure.aks.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;

public class AksComponent {

  public static AzureKubernetesService getAks(String id) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withRegion(EUROPE_WEST)
        .withNodePools(getNodePools())
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

}
