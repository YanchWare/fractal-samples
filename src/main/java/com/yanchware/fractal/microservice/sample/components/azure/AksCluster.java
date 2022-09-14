package com.yanchware.fractal.microservice.sample.components.azure;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;
import com.yanchware.fractal.sdk.valueobjects.ComponentId;
import java.util.Collection;
import java.util.List;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService.*;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureOsType.LINUX;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;

public class AksCluster {

  public static AzureKubernetesServiceBuilder getBuilder() {
    return AzureKubernetesService.builder()
        .withId(ComponentId.from("aks-cluster"))
        .withDescription("AKS cluster")
        .withDisplayName("AKS cluster")
        .withRegion(EUROPE_WEST)
        .withPodIpRange("10.3.0.0/16")
        .withServiceIpRange("10.2.0.0/16")
        .withNodePools(getNodePools());
  }

  private static Collection<? extends AzureNodePool> getNodePools() {
    return List.of(
        AzureNodePool.builder()
            .withName("linux1")
            .withOsType(LINUX)
            .withDiskSizeGb(64)
            .withInitialNodeCount(1)
            .withMachineType(STANDARD_B2S)
            .withMaxNodeCount(9)
            .withMaxPodsPerNode(30)
            .withMaxSurge(1)
            .withMinNodeCount(1)
            .withAutoscalingEnabled(true)
            .build()
    );
  }


}
