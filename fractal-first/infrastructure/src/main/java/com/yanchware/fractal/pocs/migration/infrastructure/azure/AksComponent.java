package com.yanchware.fractal.pocs.migration.infrastructure.azure;


import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.AzureNodePool;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.ManagedClusterSkuTier;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureMachineType.STANDARD_B2S;

public class AksComponent {

  public static AzureKubernetesService.AzureKubernetesServiceBuilder getComponentBuilder(
          AzureResourceGroup resourceGroup,
          String liveSystemName) {
    var aksName = String.format("%s-hosting", liveSystemName);

    return AzureKubernetesService.builder()
            .withId(aksName)
            .withDisplayName(aksName)
            .withRegion(resourceGroup.getRegion())
            .withManagedClusterSkuTier(ManagedClusterSkuTier.STANDARD)
            .withResourceGroup(resourceGroup)
            .withNodePools(getNodePools());
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
