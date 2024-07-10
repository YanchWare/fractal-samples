package com.yanchware.fractal.samples.fractalfirst.infrastructure.azure;

import com.yanchware.fractal.samples.fractalfirst.infrastructure.agnostic.ContainerizedThreeTiers;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureKubernetesService;

public class AzureContainerizedThreeTiers extends ContainerizedThreeTiers<AzureKubernetesService, AzureKubernetesService.AzureKubernetesServiceBuilder> {
    public AzureContainerizedThreeTiers() {
        super(AksComponent.getComponentBuilder(
                AzureResourceGroup.builder()
                        .withName("fractal-migration-azure")
                        .withRegion(AzureRegion.WEST_EUROPE)
                        .build()), "azure-migration-livesystem");
    }
}
