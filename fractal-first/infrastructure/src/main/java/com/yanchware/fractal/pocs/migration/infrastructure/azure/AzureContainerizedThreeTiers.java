package com.yanchware.fractal.pocs.migration.infrastructure.azure;

import com.yanchware.fractal.pocs.migration.infrastructure.agnostic.ContainerizedThreeTiers;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.AzureKubernetesService;

public class AzureContainerizedThreeTiers extends ContainerizedThreeTiers<AzureKubernetesService, AzureKubernetesService.AzureKubernetesServiceBuilder> {
    public AzureContainerizedThreeTiers(String liveSystemName) {
        super(AksComponent.getComponentBuilder(
                AzureResourceGroup.builder()
                        .withName(liveSystemName)
                        .withRegion(AzureRegion.WEST_EUROPE)
                        .build(), liveSystemName), liveSystemName);
    }
}
