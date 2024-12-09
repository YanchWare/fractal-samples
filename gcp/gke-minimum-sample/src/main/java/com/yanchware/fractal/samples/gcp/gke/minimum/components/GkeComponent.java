package com.yanchware.fractal.samples.gcp.gke.minimum.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpNodePool;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GoogleKubernetesEngine;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpMachine.E2_STANDARD2;

public class GkeComponent {

  public static GoogleKubernetesEngine getGke(String id, GcpRegion region) {
    return GoogleKubernetesEngine.builder()
        .withId(id)
        .withDisplayName(id)
        .withRegion(region)
        .withNodePools(getNodePools())
        .build();
  }

  public static Collection<? extends GcpNodePool> getNodePools() {
    return List.of(
        GcpNodePool.builder()
            .withName("nodes")
            .withMachineType(E2_STANDARD2)
            .build()
    );
  }

}
