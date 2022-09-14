package com.yanchware.fractal.microservice.sample.components.gcp;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GoogleKubernetesEngine;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpMachine;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpNodePool;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpRegion;
import com.yanchware.fractal.sdk.valueobjects.ComponentId;

import java.util.Collection;
import java.util.List;

public class GkeCluster {

  public static GoogleKubernetesEngine.GoogleKubernetesEngineBuilder getBuilder() {
    return GoogleKubernetesEngine.builder()
        .withId(ComponentId.from("gcp-cluster"))
        .withDescription("AKS cluster")
        .withDisplayName("AKS cluster")
        .withRegion(GcpRegion.EU_WEST1)
        .withNetwork("network-host")
        .withSubNetwork("compute-tier-1")
        .withPodsRange("tier-1-pods")
        .withServiceRange("tier-2-services")
        .withServiceIpMask("10.2.0.0/16")
        .withPodIpMask("10.3.0.0/16")
        .withVnetAddressSpaceIpMask("10.90.0.0/22")
        .withVnetSubnetAddressIpMask("10.90.0.0/22")
        .withNodePools(getNodePools());
  }

  private static Collection<? extends GcpNodePool> getNodePools() {
    return List.of(
        GcpNodePool.builder()
            .withName("linux1")
            .withMachineType(GcpMachine.E2_MEDIUM)
            .withDiskSizeGb(64)
            .withInitialNodeCount(1)
            .withMaxNodeCount(9)
            .withMaxNodeCount(30)
            .withMaxSurge(1)
            .withMinNodeCount(1)
            .build()
    );
  }


}
