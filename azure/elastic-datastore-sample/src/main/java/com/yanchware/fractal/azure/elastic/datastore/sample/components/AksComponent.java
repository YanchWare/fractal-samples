package com.yanchware.fractal.azure.elastic.datastore.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSElasticDataStore;
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
        .withDocumentDB(getElasticDataStoreExample())
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

  public static CaaSElasticDataStore getElasticDataStoreExample() {
    return CaaSElasticDataStore.builder()
        .withId("elastic-data")
        .withVersion("2.5.0")
        .withNamespace("elastic")
        .withKibana(true)
        .withElasticVersion("8.5")
        .withInstances(1)
        .withStorage("10Gi")
        .withStorageClassName("standard")
        .withMemory(1)
        .withCpu(1)
        .build();
  }

}
