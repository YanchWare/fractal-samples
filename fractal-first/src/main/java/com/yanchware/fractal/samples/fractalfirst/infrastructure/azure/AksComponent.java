package com.yanchware.fractal.samples.fractalfirst.infrastructure.azure;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureNodePool;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.ManagedClusterSkuTier;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureMachineType.*;

public class AksComponent {

  public static AzureKubernetesService.AzureKubernetesServiceBuilder getComponentBuilder(AzureResourceGroup resourceGroup)
  {
    var aksName = "demo-aks";

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
