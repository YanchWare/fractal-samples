package com.yanchware.fractal.samples.gcp.customworkload.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSKubernetesWorkload;
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
        .withK8sWorkload(getK8sWorkload())
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

  public static CaaSKubernetesWorkload getK8sWorkload() {
    return CaaSKubernetesWorkload.builder()
        .withId("fractal-samples")
        .withDisplayName("fractal-samples")
        .withDescription("Fractal Service on K8S")
        .withNamespace("fractal")
        .withPrivateSSHKeyPassphraseSecretId("fractal-deployer-secret-id")
        .withPrivateSSHKeySecretId("fractal-deployer-passphrase-secret-id")
        .withSSHRepositoryURI("git@github.com:YanchWare/fractal-samples.git")
        .withRepoId("YanchWare/fractal-samples")
        .withBranchName("env/prod")
        .build();
  }
}
