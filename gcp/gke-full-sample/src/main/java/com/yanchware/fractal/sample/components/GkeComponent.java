package com.yanchware.fractal.sample.components;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfiguration;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpNodePool;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GoogleKubernetesEngine;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpMachine.E2_STANDARD2;

public class GkeComponent {

  public static GoogleKubernetesEngine getGke(String id, SharedConfiguration configuration) {
    return GoogleKubernetesEngine.builder()
        .withId(id)
        .withDescription("GKE cluster with full options")
        .withDisplayName("GKE #1")
        .withRegion(configuration.getRegion())
        .withNetworkName("network-host")
        .withSubnetworkName("compute-tier-1")
        .withPodsRangeName("tier-1-pods")
        .withPodIpRange("10.3.0.0/16")
        .withServicesRangeName("tier-1-services")
        .withServiceIpRange("10.2.0.0/16")
        .withSubnetworkIpRange("10.0.4.0/22")
        .withNodePools(getNodePools())
        .build();
  }

  public static Collection<? extends GcpNodePool> getNodePools() {
    return List.of(
        GcpNodePool.builder()
            .withName("gcpnode")
            .withDiskSizeGb(35)
            .withInitialNodeCount(1)
            .withMachineType(E2_STANDARD2)
            .withMaxNodeCount(3)
            .withMaxSurge(1)
            .withMinNodeCount(1)
            .build());
  }

}
