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
        .withDescription("GKE cluster")
        .withDisplayName("GKE cluster")
        .withRegion(GcpRegion.EU_WEST1)
        .withNetworkName("fractal-demo-host")
        .withSubnetworkName("compute-tier-1")
        .withSubnetworkIpRange("10.0.4.0/22")
        .withPodsRangeName("tier-1-pods")
        .withPodIpRange("10.4.0.0/14")
        .withServicesRangeName("tier-2-services")
        .withServiceIpRange("10.0.32.0/20")

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
